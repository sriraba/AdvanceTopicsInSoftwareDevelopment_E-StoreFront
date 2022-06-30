package com.project.estorefront.model;

/**
 * Seller user (Implementation of IUser)
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   15-06-2022
 */

public class Seller extends User {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String password;
    private String sellerName;

    public Seller(String firstName, String lastName, String email, String address, String phone, String password) {
        super(firstName, lastName, email, address, phone, password);
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
