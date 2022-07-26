package com.project.estorefront.model.user;

import com.project.estorefront.model.inventory.ItemCategory;
import com.project.estorefront.model.user.ISellerPersistence;
import com.project.estorefront.model.user.Seller;
import com.project.estorefront.model.user.User;

import java.util.ArrayList;

public class SellerPersistenceMock implements ISellerPersistence {

    ArrayList<User> sellers = new ArrayList<>();
    ArrayList<User> sellersByCity = new ArrayList<>();
    ArrayList<User> sellersByCategory = new ArrayList<>();

    public SellerPersistenceMock() {
    }

    public void addMockSeller() {
        Seller seller = new Seller();
        seller.setUserID("1");
        seller.setEmail("hrishipatel99@gmail.com");
        seller.setBusinessName("ASD");
        seller.setBusinessDescription("ASD");
        sellers.add(seller);

    }

    public void addMockSellerWithCity(String cityName) {
        Seller seller = new Seller();
        seller.setEmail("hrishipatel99@gmail.com");
        seller.setBusinessName("ASD");
        seller.setBusinessDescription("ASD");
        seller.setCity(cityName);
        sellersByCity.add(seller);
    }

    public void addMockSellerForCategoryTest() {
        Seller seller = new Seller();
        seller.setEmail("hrishipatel99@gmail.com");
        seller.setBusinessName("ASD");
        seller.setBusinessDescription("ASD");
        seller.setCity("Halifax");
        sellersByCategory.add(seller);
    }

    @Override
    public ArrayList<User> getAllSellers() {
        for (User seller : sellers) {
            System.out.println(seller.getEmail());
        }
        if (sellers.size() == 0) {
            return null;
        }
        return sellers;
    }


    @Override
    public ArrayList<User> getAllSellersByCity(String city) {
        ArrayList<User> s = new ArrayList<>();
        for (User seller : sellersByCity) {
            if (seller.getCity().equalsIgnoreCase(city)) {
                s.add(seller);
            }
        }
        if (s.size() == 0) {
            return null;
        }
        return s;
    }

    @Override
    public ArrayList<User> getAllSellersByCategory(ItemCategory itemCategory, String city) {
        return sellersByCategory.size() > 0 ? sellersByCategory : null;
    }

    @Override
    public User getSellerByID(String sellerID) {
        for (User seller : sellers) {
            if (seller.getUserID().equalsIgnoreCase(sellerID)) {
                return seller;
            }
        }
        return null;
    }

    @Override
    public boolean deactivateSellerAccount(User seller) {
        //User seller = new Seller();
        for (User u: sellers){
            if(u.getUserID() == seller.getUserID()){
                seller.setIsSeller(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateSellerAccount(User seller) {
        Seller currentSeller;
        for (User u: sellers ) {
            if (u.getUserID().equals(seller.getUserID())){
                currentSeller = (Seller) u;
                currentSeller.setFirstName(seller.getFirstName());
                currentSeller.setLastName(seller.getLastName());
                currentSeller.setBusinessName(((Seller)seller).getBusinessName());
                currentSeller.setPhone(seller.getPhone());
                //currentSeller.setEmail(seller.getEmail());
                return true;
            }
        }
        return false;
    }
}
