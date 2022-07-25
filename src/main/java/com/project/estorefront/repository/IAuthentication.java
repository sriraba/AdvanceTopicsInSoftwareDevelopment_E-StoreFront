package com.project.estorefront.repository;

import com.project.estorefront.model.User;

import java.sql.SQLException;

public interface IAuthentication {
    String login(String email, String password) throws SQLException;

    String register(User user) throws SQLException;

    boolean resetPassword(String email, String password) throws SQLException;

    boolean checkIfUserExists(String email) throws SQLException;

    boolean checkIfUserIsSeller(String email) throws SQLException;
}
