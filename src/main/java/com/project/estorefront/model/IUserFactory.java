package com.project.estorefront.model;

public interface IUserFactory {

    User getUser(String userType);

    User makeUserWithAllFields(String userID, String firstName, String lastName, String email, String address, String phone, String city, boolean isSeller, String businessName, String businessDescription);

}
