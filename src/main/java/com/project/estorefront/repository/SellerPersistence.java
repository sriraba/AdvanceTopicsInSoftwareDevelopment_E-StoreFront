package com.project.estorefront.repository;

import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;
import com.project.estorefront.model.UserFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
