package com.project.estorefront.repository;

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
 /* Reference:
  [1]https://docs.oracle.com/cd/E11882_01/appdev.112/e12137/upddata.htm#TDPJD204
 */
    public User findSellerById(int id) throws SQLException {

        User selectedSeller = new Seller();
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Seller WHERE seller_id = " + id;
        System.out.println("\nExecuting: " + query);
        ResultSet rset = stmt.executeQuery(query);
        while (rset.next()) {
            selectedSeller.setUserID(rset.getString("user_id"));
            ((Seller) selectedSeller).setBusinessName(rset.getString("business_name"));
            selectedSeller.setFirstName(rset.getString("first_name"));
            selectedSeller.setLastName(rset.getString("last_name"));
            selectedSeller.setEmail(rset.getString("email"));
            selectedSeller.setPhone(rset.getString("phone_number"));
//          selectedSeller.setDob(rset.getString("Dob"));

        }
        return selectedSeller;
    }

    // logic to update profile
    public String updateSellerProfile(int seller_id, String business_name, String first_name, String last_name, String phone_number, String email) throws SQLException {
        Seller oldSeller = null;
        try {
            oldSeller = (Seller) findSellerById(seller_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        StringBuffer columns = new StringBuffer(255);
        if (first_name != null &&
                !first_name.equals(oldSeller.getFirstName())) {
            columns.append("first_name = '" + first_name + "'");
        }
        if (last_name != null &&
                !last_name.equals(oldSeller.getLastName())) {
            if (columns.length() > 0) {
                columns.append(", ");
            }
            columns.append("last_name = '" + last_name + "'");
        }
        if (business_name != null &&
                !first_name.equals(oldSeller.getBusinessName())) {
            columns.append("business_name = '" + business_name + "'");
        }
//        if (Dob != null &&
//                !Dob.equals(oldSeller.getDob())) {
//            columns.append("Dob = '" + Dob + "'");
//        }
        if (email != null &&
                !email.equals(oldSeller.getEmail())) {
            if (columns.length() > 0) {
                columns.append(", ");
            }
            columns.append("email = '" + email + "'");
        }
        if (phone_number != null &&
                !phone_number.equals(oldSeller.getPhone())) {
            if (columns.length() > 0) {
                columns.append(", ");
            }
            columns.append("phone_number = '" + phone_number + "'");
        }
        if (columns.length() > 0) {
            String sqlString =
                    "UPDATE Sellers SET " + columns.toString() +
                            " WHERE seller_id= " + oldSeller;
            System.out.println("\nExecuting: " + sqlString);
            stmt.execute(sqlString);
        } else {
            System.out.println("Nothing to do to update Seller Id: " +oldSeller);
        }
        return "success";
    }

    @Override
    public String deleteSellerAccount(int id) throws SQLException {
        Connection connection = Database.getConnection();
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sqlString = "DELETE FROM Seller WHERE seller_id = " + id;
        System.out.println("\nExecuting: " + sqlString);
        stmt.execute(sqlString);
        return "success";
    }
}
