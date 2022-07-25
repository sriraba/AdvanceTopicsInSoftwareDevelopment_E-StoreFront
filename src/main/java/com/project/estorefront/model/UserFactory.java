package com.project.estorefront.model;

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
}

