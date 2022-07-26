package com.project.estorefront.model.user;

public class UserFactory implements IUserFactory {

    private static UserFactory instance = null;

    private UserFactory() {
    }

    public static IUserFactory instance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    public User getUser(String userType) {

        if (userType.equalsIgnoreCase("buyer")) {
            return BuyerFactory.instance().makeBuyer();
        } else if (userType.equalsIgnoreCase("seller")) {
            return SellerFactory.instance().makeSeller();
        } else {
            return null;
        }

    }

    @Override
    public User makeUserWithAllFields(String userID, String firstName, String lastName, String email, String address, String phone, String city, boolean isSeller, String businessName, String businessDescription) {
        User seller = new Seller();
        seller.setUserID(userID);
        seller.setFirstName(firstName);
        seller.setLastName(lastName);
        seller.setEmail(email);
        seller.setAddress(address);
        seller.setPhone(phone);
        seller.setCity(city);
        seller.setIsSeller(isSeller);
        ((Seller) seller).setBusinessName(businessName);
        ((Seller) seller).setBusinessDescription(businessDescription);

        return seller;
    }
}

