package com.project.eStorefront.model;

/**
 * Buyer user (Implementation of IUser)
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   15-06-2022
 */

public class Buyer implements IUser {

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String password;

    public Buyer(String firstName, String lastName, String email, String address, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
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
