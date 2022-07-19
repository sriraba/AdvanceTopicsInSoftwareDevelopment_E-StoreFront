package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

import java.sql.SQLException;
import java.util.ArrayList;


public class Seller extends User implements ISeller {

    private String businessName;
    private String businessDescription;

    public Seller() {
        super();
    }

    @Override
    public void updateProfile() {

    }

//    public String updateProfile(ISellerPersistence persistence, int seller_id, String business_name, String first_name, String last_name, String phone_number, String email) throws SQLException {
//        return persistence.updateSellerProfile(seller_id, business_name,  first_name, last_name,  phone_number, email);
//
//    }

    @Override
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public boolean updateProfile(ISellerPersistence persistence) {
        return false;
    }

    @Override
    public boolean deleteProfile(ISellerPersistence persistence) {
        return false;
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

//    public String deleteProfile(ISellerPersistence persistence , int id) throws SQLException {
//        return persistence.deleteSellerAccount(id);
//    }

    public static ArrayList<User> getAllSellers(ISellerPersistence persistence, String city) {
        return persistence.getAllSellersByCity(city);
    }

    public static ArrayList<User> getAllSellersByCategory(ISellerPersistence persistence, ItemCategory category, String city) {
        return persistence.getAllSellersByCategory(category, city);
    }
}
