package com.project.estorefront.model.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.project.estorefront.model.database.IDatabase;
import com.project.estorefront.model.user.User;
import com.project.estorefront.model.user.UserFactory;

public class Authentication implements IAuthentication {

    private final IDatabase database;

    public Authentication(IDatabase database) {
        this.database = database;
    }

    @Override
    public User login(String email, String password) throws SQLException {
        Connection connection = database.getConnection();
        try {
            String userDetailsQuery = "select * from user where email =?";
            PreparedStatement preparedStmt = connection.prepareStatement(userDetailsQuery);
            preparedStmt.setString(1, email);
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                if (ICryptoFactory.CryptoFactory.instance().makeCrypto().checkPassword(password, hashedPassword)) {
                    String userID = resultSet.getString("user_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("contact_num");
                    String city = resultSet.getString("city");

                    boolean isSeller = resultSet.getBoolean("seller");
                    String businessName = null;
                    String businessDescription = null;
                    if (isSeller) {
                        businessName = resultSet.getString("business_name");
                        businessDescription = resultSet.getString("business_description");
                    }
                    return UserFactory.instance().makeUserWithAllFields(userID, firstName, lastName, email, address, phone, city, isSeller, businessName, businessDescription);
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
    public String register(String firstName, String lastName, String email, String password, String phone,
                           boolean isSeller, String city, String businessName, String address, String businessDescription,
                           boolean isUserEnabled) throws SQLException {
        Connection connection = database.getConnection();

        String userID = UUID.randomUUID().toString();
        String hashedPassword = ICryptoFactory.CryptoFactory.instance().makeCrypto().encryptPassword(password);

        try {
            String persistUserDetails = "insert into user (user_id, first_name, last_name, email, password, contact_num, seller, city, business_name, address, business_description, isUserEnabled) "
                    +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(persistUserDetails);

            preparedStmt.setString(1, userID);
            preparedStmt.setString(2, firstName);
            preparedStmt.setString(3, lastName);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, hashedPassword);
            preparedStmt.setString(6, phone);
            preparedStmt.setBoolean(7, isSeller);
            preparedStmt.setString(8, city);
            preparedStmt.setString(9, businessName);
            preparedStmt.setString(10, address);
            preparedStmt.setString(11, businessDescription);
            preparedStmt.setBoolean(12, isUserEnabled);

            preparedStmt.execute();
            return userID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public boolean resetPassword(String email, String password) throws SQLException {
        Connection connection = database.getConnection();

        try {
            String resetPasswordQuery = "update user set password = ? where email = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(resetPasswordQuery);
            String hashedPassword = ICryptoFactory.CryptoFactory.instance().makeCrypto().encryptPassword(password);
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
    public boolean checkIfUserExists(String email) throws SQLException {
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
    public boolean checkIfUserIsSeller(String email) throws SQLException {
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
