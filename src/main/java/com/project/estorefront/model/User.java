package com.project.estorefront.model;

import com.project.estorefront.repository.IAuthentication;

public abstract class User {

    protected String userID;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String address;
    protected String city;
    protected String phone;
    protected String password;
    protected boolean isSeller;
    protected boolean isUserEnabled;

    public User(String firstName, String lastName, String email, String address, String phone, String password,
            String city, boolean isSeller, boolean isUserEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.isSeller = isSeller;
        this.isUserEnabled = isUserEnabled;
    }

    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public abstract void updateSellerProfile();

    public abstract void updateProfile();

    // public abstract void updateSellerProfile();

    public void deleteProfile() {

    }

    public boolean getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(boolean isSeller) {
        this.isSeller = isSeller;
    }

    public boolean getIsUserEnabled() {
        return isUserEnabled;
    }

    public void setIsUserEnabled(boolean isUserEnabled) {
        this.isUserEnabled = isUserEnabled;
    }

    public static String login(IAuthentication authentication, String email, String password) {
        return authentication.login(email, password);
    };

    public String register(IAuthentication authentication) {
        return authentication.register(this);
    };

    public static boolean sendResetEmail(IMailSender mailSender, String email, String otp) {
        return mailSender.sendMail(email, otp);
    }

    public static boolean resetPassword(IAuthentication authentication, String email, String password) {
        return authentication.resetPassword(email, password);
    }

}
