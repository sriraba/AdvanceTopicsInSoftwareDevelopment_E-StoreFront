package com.project.estorefront.model;

/**
 * Buyer user (Implementation of IUser)
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   15-06-2022
 */

public class Buyer extends User {


    public Buyer(String firstName, String lastName, String email, String address, String phone, String password, String city, String isSeller) {
        super(firstName, lastName, email, address, phone, password, city, isSeller);
    }

    @Override
    public void updateProfile() {
        // TODO To be implemented
    }

    @Override
    public void deleteProfile() {
        // TODO To be implemented
    }
}
