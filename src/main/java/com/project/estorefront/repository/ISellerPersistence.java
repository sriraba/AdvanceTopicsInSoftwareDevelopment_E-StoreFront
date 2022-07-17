package com.project.estorefront.repository;


import java.sql.SQLException;

import com.project.estorefront.model.User;

import java.util.ArrayList;

public interface ISellerPersistence {
    String updateSellerProfile(int seller_id, String business_name, String first_name, String last_name, String phone_number, String email) throws SQLException;

    String deleteSellerAccount(int id) throws SQLException;

    ArrayList<User> getAllSellers() throws SQLException;
    ArrayList<User> getAllSellersByCity(String city);
}

