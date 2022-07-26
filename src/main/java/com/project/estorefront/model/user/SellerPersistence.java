package com.project.estorefront.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.database.IDatabase;
import com.project.estorefront.model.inventory.ItemCategory;

public class SellerPersistence implements ISellerPersistence {

    private IDatabase database;

    public SellerPersistence(IDatabase database) {
        this.database = database;
    }

    @Override
    public ArrayList<User> getAllSellers() throws SQLException {
        Connection connection = database.getConnection();
        ArrayList<User> sellerList = new ArrayList<>();

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

                User seller = UserFactory.instance().makeUserWithAllFields(userID, firstName, lastName, email, address,
                        contactNumber, city, true, businessName, businessDescription);

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
    public ArrayList<User> getAllSellersByCity(String city) throws SQLException {
        ArrayList<User> sellerList = new ArrayList<>();
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

                User seller = UserFactory.instance().makeUserWithAllFields(userID, firstName, lastName, email, address,
                        contactNumber, sellerCity, true, businessName, businessDescription);

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
    public ArrayList<User> getAllSellersByCategory(ItemCategory itemCategory, String city) throws SQLException {
        ArrayList<User> sellerList = new ArrayList<>();
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

                User seller = UserFactory.instance().makeUserWithAllFields(userID, null, null, email, address,
                        contactNumber, rsCity, true, businessName, businessDescription);

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
    public User getSellerByID(String sellerID) throws SQLException {
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

                User seller = UserFactory.instance().makeUserWithAllFields(userID, firstName, lastName, email, address,
                        contactNumber, city, true, businessName, businessDescription);

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
    public boolean deactivateSellerAccount(User seller) throws SQLException {
        PreparedStatement preparedStatement = null;
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
    public boolean updateSellerAccount(User seller) throws SQLException {
        PreparedStatement preparedStatement = null;
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
