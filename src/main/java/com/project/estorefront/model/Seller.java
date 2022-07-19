package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

import java.util.ArrayList;

public class Seller extends User implements ISeller {

    private String businessName;
    private String businessDescription;

    public Seller() {
        super();
    }


    @Override
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String getBusinessName() {
        return businessName;
    }

    @Override
    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    @Override
    public String getBusinessDescription() {
        return businessDescription;
    }


    public static ArrayList<User> getAllSellers(ISellerPersistence persistence, String city) {
        return persistence.getAllSellersByCity(city);
    }

    public static ArrayList<User> getAllSellersByCategory(ISellerPersistence persistence, ItemCategory category, String city) {
        return persistence.getAllSellersByCategory(category, city);
    }
    @Override
    public boolean updateProfile(ISellerPersistence persistence) {
        return persistence.updateSellerProfile(this);
    }

    @Override
    public boolean deleteProfile(ISellerPersistence persistence) {
        return false;
    }
    @Override
    public void updateProfile() {

    }
    public User getSellerByID(ISellerPersistence persistence ,String sellerID){
        return persistence.getSellerByID(sellerID);
    }
}
