package com.project.estorefront.model;

public abstract class User {

    private String firstName;
    private String lastName;
    private String email;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String phone;
    private String password;

    public User(String firstName, String lastName, String email, String address, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
    }

    public void updateProfile() {

    }

    public void deleteProfile() {

    }
}
