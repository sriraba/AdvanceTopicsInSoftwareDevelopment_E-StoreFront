package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

import java.util.ArrayList;

/**
 * Seller user (Implementation of IUser)
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   15-06-2022
 */

public class Seller extends User implements ISeller {

    private String businessName;
    private String businessDescription;

    public Seller() {
        super();
    }

    @Override
    public void updateProfile() {

    }

    public Seller(String firstName, String lastName, String email, String address, String contact, String password, String city, boolean b) {
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    @Override
    public void deleteProfile() {
    }

    @Override
    public ArrayList<User> getAllSellers(ISellerPersistence persistence, String city) {
        return persistence.getAllSellersByCity(city);
    }
}
