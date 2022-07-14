package com.project.estorefront.repository;

import com.project.estorefront.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Seller implements ISellerPersistence {
    Statement stmt = null;
    Connection connection;

    public Seller findSellerById(int id) throws SQLException {

        User selectedSeller = new Seller();
        connection = Database.getConnection();
        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String query = "SELECT * FROM Seller WHERE seller_id = " + id;
        System.out.println("\nExecuting: " + query);
        ResultSet rset = stmt.executeQuery(query);
        while (rset.next()) {
            selectedSeller.setSellerId(new Integer(rset.getInt("seller_id")));
            selectedSeller.setBusinessName(rset.getString("business_name"));
            selectedSeller.setFirstName(rset.getString("first_name"));
            selectedSeller.setLastName(rset.getString("last_name"));
            selectedSeller.setEmail(rset.getString("email"));
            selectedSeller.setPhoneNumber(rset.getString("phone_number"));
            selectedSeller.setDob(rset.getString("Dob"));

        }
        return selectedSeller;
    }

    // logic to update profile
    public String updateSellerProfile(String business_name, String first_name, String last_name, String Dob, String phone_number, String email ) throws SQLException
    {
        Seller oldSeller = findSellerById(seller_id);
        connection = Database.getConnection();
        stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        StringBuffer columns = new StringBuffer( 255 );
        if ( first_name != null &&
                !first_name.equals(oldSeller.getFirstName() ) )
        {
            columns.append( "first_name = '" + first_name + "'" );
        }
        if ( last_name != null &&
                !last_name.equals(oldSeller.getLastName() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
            }
            columns.append( "last_name = '" + last_name + "'" );
        }
        if ( business_name != null &&
                !first_name.equals(oldSeller.getBusinessName() ) )
        {
            columns.append( "business_name = '" + business_name + "'" );
        }
        if ( Dob != null &&
                !Dob.equals(oldSeller.getDob() ) )
        {
            columns.append( "Dob = '" + Dob + "'" );
        }
        if ( email != null &&
                !email.equals( oldSeller.getEmail() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
            }
            columns.append( "email = '" + email + "'" );
        }
        if ( phone_number != null &&
                !phone_number.equals( oldSeller.getPhoneNumber() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
            }
            columns.append( "phone_number = '" + phone_number + "'" );
        }
        if ( columns.length() > 0 )
        {
            String sqlString =
                    "UPDATE Sellers SET " + columns.toString() +
                            " WHERE seller_id= " + seller_id;
            System.out.println("\nExecuting: " + sqlString);
            stmt.execute(sqlString);
        }
        else
        {
            System.out.println( "Nothing to do to update Employee Id: " + seller_id);
        }
        return "success";
    }


    @Override
    public String deleteSellerAccount() throws SQLException {
        connection = Database.getConnection();
        stmt =
                connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
        String sqlString = "DELETE FROM Seller WHERE seller_id = " + id;
        System.out.println("\nExecuting: " + sqlString);
        stmt.execute(sqlString);
        return "success";
    }

}

    //Also write test cases

