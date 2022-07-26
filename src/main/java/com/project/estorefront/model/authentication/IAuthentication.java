package com.project.estorefront.model.authentication;

import com.project.estorefront.model.user.User;

import java.sql.SQLException;

public interface IAuthentication {
    User login(String email, String password) throws SQLException;

    String register(String firstName, String lastName, String email, String password, String phone, boolean isSeller,
            String city, String businessName, String address, String businessDescription, boolean isUserEnabled)
            throws SQLException;

    boolean resetPassword(String email, String password) throws SQLException;

    boolean checkIfUserExists(String email) throws SQLException;

    boolean checkIfUserIsSeller(String email) throws SQLException;
}
