package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.project.estorefront.model.CryptoFactory;
import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.User;

public class Authentication implements IAuthentication {

    @Override
    public String login(String email, String password) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();
        try {
            String userDetailsQuery = "select * from user where email =?";
            PreparedStatement preparedStmt = connection.prepareStatement(userDetailsQuery);
            preparedStmt.setString(1, email);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                if (CryptoFactory.instance().makeCrypto().checkPassword(password, hashedPassword)) {
                    return resultSet.getString("user_id");
                }
                return null;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public String register(User user) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            String persistUserDetails = "insert into user (user_id, first_name, last_name, email, password, contact_num, seller, city, business_name, address, isUserEnabled) "
                    +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(persistUserDetails);

            String userID = UUID.randomUUID().toString();
            String hashedPassword = CryptoFactory.instance().makeCrypto().encryptPassword(user.getPassword());

            preparedStmt.setString(1, userID);
            preparedStmt.setString(2, user.getFirstName());
            preparedStmt.setString(3, user.getLastName());
            preparedStmt.setString(4, user.getEmail());
            preparedStmt.setString(5, hashedPassword);
            preparedStmt.setString(6, user.getPhone());
            preparedStmt.setBoolean(7, user.getIsSeller());
            preparedStmt.setString(8, user.getCity());
            preparedStmt.setString(9, "");
            preparedStmt.setString(10, user.getAddress());
            preparedStmt.setBoolean(11, user.getIsUserEnabled());
            preparedStmt.execute();
            return userID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public boolean resetPassword(String email, String password) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();
        try {
            String resetPasswordQuery = "update user set password = ? where email = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(resetPasswordQuery);
            String hashedPassword = CryptoFactory.instance().makeCrypto().encryptPassword(password);
            preparedStmt.setString(1, hashedPassword);
            preparedStmt.setString(2, email);

            return preparedStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public boolean checkIfUserExists(String email) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();
        try {
            String checkIfUserExistsQuery = "select * from user where email = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(checkIfUserExistsQuery);
            preparedStmt.setString(1, email);
            ResultSet resultSet = preparedStmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public boolean checkIfUserIsSeller(String email) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();
        try {
            String checkIfUserIsSellerQuery = "select * from user where email = ? and seller = 1";
            PreparedStatement preparedStmt = connection.prepareStatement(checkIfUserIsSellerQuery);
            preparedStmt.setString(1, email);
            ResultSet resultSet = preparedStmt.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }
}
