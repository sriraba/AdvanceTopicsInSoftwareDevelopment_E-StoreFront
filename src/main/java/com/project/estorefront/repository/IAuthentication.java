package com.project.estorefront.repository;

import com.project.estorefront.model.User;

import java.sql.SQLException;

public interface IAuthentication {
    String login(String email, String password) throws SQLException;

    String register(String firstName, String lastName, String email, String password, String phone, boolean isSeller, String city, String businessName, String address, String businessDescription, boolean isUserEnabled) throws SQLException;

    boolean resetPassword(String email, String password) throws SQLException;

    boolean checkIfUserExists(String email) throws SQLException;

    boolean checkIfUserIsSeller(String email) throws SQLException;
}
