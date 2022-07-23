package com.project.estorefront.repository;

import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;

import java.sql.SQLException;
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
    public ArrayList<User> getAllSellers() throws SQLException {
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
}
