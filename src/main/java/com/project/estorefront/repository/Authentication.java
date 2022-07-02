package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;

public class Authentication implements IAuthentication {

    private Connection connection = null;
    private ResultSet resultSet = null;

    @Override
    public Integer login(String email, String password) {

        try {
            User user = null;
            connection = Database.getConnection();
            String userDetailsQuery = "select * from user where email =? and password =?";
            PreparedStatement preparedStmt = connection.prepareStatement(userDetailsQuery);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, password);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                Integer userID = resultSet.getInt("user_id");
				return userID;
            }
			return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer register(User user) {

        try {
            connection = Database.getConnection();
            String persistUserDetails = "insert into user (user_id, first_name, last_name, email, password, contact_num, seller, city, business_name ) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(persistUserDetails);

            int userID = generateNewUserID();

            preparedStmt.setInt(1, userID);
            preparedStmt.setString(2, user.getFirstName());
            preparedStmt.setString(3, user.getLastName());
            preparedStmt.setString(4, user.getEmail());
            preparedStmt.setString(5, user.getPassword());
            preparedStmt.setString(6, user.getPhone());
            preparedStmt.setBoolean(7, user.getIsSeller());
            preparedStmt.setString(8, user.getCity());
            preparedStmt.setString(9, "");
            preparedStmt.execute();
            return userID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer generateNewUserID() {
        try {
            int userId = 0;
            connection = Database.getConnection();
            String userDetailsQuery = "select MAX(user_id) from user";
            PreparedStatement preparedStmt = connection.prepareStatement(userDetailsQuery);
            resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            return userId + 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
