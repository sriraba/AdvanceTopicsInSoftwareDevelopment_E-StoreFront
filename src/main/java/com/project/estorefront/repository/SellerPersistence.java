package com.project.estorefront.repository;

import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;
import com.project.estorefront.model.UserFactory;

import java.sql.*;
import java.util.ArrayList;

public class SellerPersistence implements ISellerPersistence {

    @Override
    public ArrayList<User> getAllSellers() {
        ArrayList<User> sellerList = new ArrayList<>();
        Connection connection = Database.getConnection();
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
                ((Seller) seller).setBusinessName(businessName);
                ((Seller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerList;
        }
    }

    @Override
    public ArrayList<User> getAllSellersByCity(String city) {
        ArrayList<User> sellerList = new ArrayList<>();
        Connection connection = Database.getConnection();
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
                ((Seller) seller).setBusinessName(businessName);
                ((Seller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerList;
        }
    }

    @Override
    public ArrayList<User> getAllSellersByCategory(ItemCategory itemCategory, String city) {
        ArrayList<User> sellerList = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT DISTINCT user.user_id, email, contact_num, seller, city, business_name, address, business_description, seller_inventory.category_id FROM user RIGHT JOIN seller_inventory on user.user_id = seller_inventory.user_id where user.seller = 1 AND category_id = ? AND city = ?;");
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
                ((Seller) seller).setBusinessName(businessName);
                ((Seller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                sellerList.add(seller);
            }
            return sellerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerList;
        }
    }

    @Override
    public User getSellerByID(String sellerID) {
        Connection connection = Database.getConnection();
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
                ((Seller) seller).setBusinessName(businessName);
                ((Seller) seller).setBusinessDescription(businessDescription);

                seller.setUserID(userID);
                return seller;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deactivateSellerAccount() {
        User seller = new Seller();
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        try {
            preparedStatement = connection.prepareStatement("update FROM user WHERE user_id = ?");
            preparedStatement.setString(1, seller.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return false;
    }
    @Override
    public boolean updateSellerProfile(User seller) {

            PreparedStatement preparedStatement = null;
            Connection connection = Database.getConnection();
            try {
                preparedStatement = connection.prepareStatement("UPDATE user SET user_id = ?, first_name = ?, last_name = ?, email = ?, contact_num = ?, business_name = ? WHERE user_id = ?");
                preparedStatement.setString(1, seller.getUserID());
                preparedStatement.setString(2, seller.getFirstName());
                preparedStatement.setString(3, seller.getLastName());
                preparedStatement.setString(4, seller.getEmail());
                preparedStatement.setString(5, seller.getPhone());
                preparedStatement.setString(6, ((Seller)seller).getBusinessName());
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}


