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

    public Seller(String sellerID) {
        super();
        this.userID = sellerID;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @Override
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    @Override
    public String getBusinessDescription() {
        return businessDescription;
    }


    public static ArrayList<User> getAllSellersByCity(ISellerPersistence persistence, String city) throws SQLException {
        return persistence.getAllSellersByCity(city);
    }

    public static ArrayList<User> getAllSellersByCategory(ISellerPersistence persistence, ItemCategory category, String city) throws SQLException {
        return persistence.getAllSellersByCategory(category, city);
    }

    @Override
    public void updateProfile() {

    }

    public User getSellerByID(ISellerPersistence persistence, String sellerID) throws SQLException {
        return persistence.getSellerByID(sellerID);
    }

    @Override
    public boolean updateSellerAccount(ISellerPersistence persistence) throws SQLException {
        return persistence.updateSellerAccount(this);
    }

    @Override
    public boolean deactivateSellerAccount(ISellerPersistence persistence) throws SQLException {
        return persistence.deactivateSellerAccount(this);
    }

}
