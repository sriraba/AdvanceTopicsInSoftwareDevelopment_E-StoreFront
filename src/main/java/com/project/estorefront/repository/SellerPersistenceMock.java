package com.project.estorefront.repository;

import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;

import java.util.ArrayList;

public class SellerPersistenceMock implements ISellerPersistence {

    ArrayList<User> sellers = new ArrayList<>();
    ArrayList<User> sellersByCity = new ArrayList<>();

    public SellerPersistenceMock() {
    }

    public void addMockSeller() {
        Seller seller = new Seller();
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

    @Override
    public String updateSellerProfile(int seller_id, String business_name, String first_name, String last_name, String phone_number, String email) {
        return "success" ; //check for return
    }

    @Override
    public String deleteSellerAccount(int id) {
        return null;
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
}
