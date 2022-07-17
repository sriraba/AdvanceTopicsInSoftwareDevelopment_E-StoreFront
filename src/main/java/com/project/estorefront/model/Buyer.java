package com.project.estorefront.model;

/**
 * Buyer user (Implementation of IUser)
 *
 * @author Hrishi Patel
 * @version 1.0
 * @since 15-06-2022
 */

public class Buyer extends User {


    public Buyer() {
        super();
    }

    public Buyer(String firstName, String lastName, String email, String address, String contact, String password, String city, boolean b) {
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
