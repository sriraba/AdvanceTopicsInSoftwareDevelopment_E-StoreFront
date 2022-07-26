package com.project.estorefront.model.user;

import com.project.estorefront.model.database.IDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyerPersistence implements IBuyerPersistence {

    private IDatabase database;

    public BuyerPersistence(IDatabase database) {
        this.database = database;
    }

    @Override
    public User getBuyerByID(String buyerID) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            preparedStatement.setString(1, buyerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_num");
                String city = rs.getString("city");
                String address = rs.getString("address");
                User buyer = UserFactory.instance().getUser("buyer");
                buyer.setFirstName(firstName);
                buyer.setLastName(lastName);
                buyer.setEmail(email);
                buyer.setAddress(address);
                buyer.setPhone(contactNumber);
                buyer.setCity(city);
                buyer.setIsUserEnabled(true);
                buyer.setUserID(userID);
                return buyer;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBuyerAccount(User buyer) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE user SET first_name = ?, last_name = ?, contact_num = ?, address  =? WHERE user_id = ?");
            preparedStatement.setString(1, buyer.getFirstName());
            preparedStatement.setString(2, buyer.getLastName());
            preparedStatement.setString(3, buyer.getPhone());
            preparedStatement.setString(4, buyer.getAddress());
            //preparedStatement.setString(, buyer.getEmail());
            preparedStatement.setString(5, buyer.getUserID());
            preparedStatement.executeUpdate();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean deactivateBuyerAccount(User buyer) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("UPDATE user SET isUserEnabled =? WHERE user_id = ?");
            preparedStatement.setBoolean(1, buyer.getIsUserEnabled());
            preparedStatement.setString(2, buyer.getUserID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
