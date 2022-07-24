package com.project.estorefront.repository;

import com.project.estorefront.model.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.*;

public class SellerPersistence implements ISellerPersistence {

    @Override
    public ArrayList<User> getAllSellers() {
        ArrayList<User> sellerList = new ArrayList<>();
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE seller = 1");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String businessName = rs.getString("business_name");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_num");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String businessDescription = rs.getString("business_description");

                User seller = UserFactory.instance().getUser("seller");
                seller.setFirstName(firstName);
                seller.setLastName(lastName);
                seller.setEmail(email);
                seller.setAddress(address);
                seller.setPhone(contactNumber);
                seller.setCity(city);
                seller.setIsSeller(true);
                ((ISeller) seller).setBusinessName(businessName);
                ((ISeller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerList;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public ArrayList<User> getAllSellersByCity(String city) {
        ArrayList<User> sellerList = new ArrayList<>();
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE seller = 1 AND city = ?");
            preparedStatement.setString(1, city);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String businessName = rs.getString("business_name");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_num");
                String sellerCity = rs.getString("city");
                String address = rs.getString("address");
                String businessDescription = rs.getString("business_description");

                User seller = UserFactory.instance().getUser("seller");
                seller.setFirstName(firstName);
                seller.setLastName(lastName);
                seller.setEmail(email);
                seller.setAddress(address);
                seller.setPhone(contactNumber);
                seller.setCity(sellerCity);
                seller.setIsSeller(true);
                ((ISeller) seller).setBusinessName(businessName);
                ((ISeller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerList;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public ArrayList<User> getAllSellersByCategory(ItemCategory itemCategory, String city) {
        ArrayList<User> sellerList = new ArrayList<>();
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT DISTINCT user.user_id, email, contact_num, seller, city, business_name, address, business_description, seller_inventory.category_id FROM user RIGHT JOIN seller_inventory on user.user_id = seller_inventory.user_id where user.seller = 1 AND category_id = ? AND city = ?;");
            preparedStatement.setString(1, itemCategory.toString());
            preparedStatement.setString(2, city);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_num");
                String businessName = rs.getString("business_name");
                String rsCity = rs.getString("city");
                String address = rs.getString("address");
                String businessDescription = rs.getString("business_description");
                ItemCategory category = ItemCategory.valueOf(rs.getString("category_id"));

                User seller = UserFactory.instance().getUser("seller");
                seller.setEmail(email);
                seller.setAddress(address);
                seller.setPhone(contactNumber);
                seller.setCity(rsCity);
                seller.setIsSeller(true);
                ((ISeller) seller).setBusinessName(businessName);
                ((ISeller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerList;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public User getSellerByID(String sellerID) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            preparedStatement.setString(1, sellerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String businessName = rs.getString("business_name");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_num");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String businessDescription = rs.getString("business_description");

                User seller = UserFactory.instance().getUser("seller");
                seller.setFirstName(firstName);
                seller.setLastName(lastName);
                seller.setEmail(email);
                seller.setAddress(address);
                seller.setPhone(contactNumber);
                seller.setCity(city);
                seller.setIsSeller(true);
                ((ISeller) seller).setBusinessName(businessName);
                ((ISeller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                return seller;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public boolean deactivateSellerAccount(User seller) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            preparedStatement = connection.prepareStatement("UPDATE user SET seller =? WHERE user_id = ?");
            preparedStatement.setBoolean(1, seller.getIsSeller());
            preparedStatement.setString(2, seller.getUserID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSellerAccount(User seller) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE user SET first_name = ?, last_name = ?, contact_num = ?, business_name =?, business_description=? WHERE user_id = ?");
            // preparedStatement.setString(1, seller.getUserID());
            preparedStatement.setString(1, seller.getFirstName());
            preparedStatement.setString(2, seller.getLastName());
            preparedStatement.setString(3, seller.getPhone());
            preparedStatement.setString(4, ((Seller) seller).getBusinessName());
            preparedStatement.setString(5, ((Seller) seller).getBusinessDescription());
            preparedStatement.setString(6, seller.getUserID());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
