package com.project.estorefront.repository;

import com.project.estorefront.model.Coupon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CouponsPersistence implements ICouponsPersistence{

    Connection connection;

    public CouponsPersistence()
    {
        connection = Database.getConnection();
    }
    @Override
    public void saveCoupon(Coupon coupon) {

        try {

            String query = "insert into coupons values (?,?,?,?,?)" ;

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, coupon.getCouponID());
            preparedStmt.setInt(2, 1);
            preparedStmt.setString(3, coupon.getName());
            preparedStmt.setString(4, Double.toString(coupon.getMaxAmount()));
            preparedStmt.setString(5, Double.toString(coupon.getPercent()));
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public List<Coupon> getCoupons() {
        ArrayList<Coupon> coupons = new ArrayList<>();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "select * from coupons;" ;
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next())
            {
                int coupon_id = rs.getInt("coupon_id");
                String name = rs.getString("name");
                double max_amt = Double.parseDouble(rs.getString("max_amt"));
                double percent = Double.parseDouble(rs.getString("percent"));

                Coupon coupon = new Coupon(coupon_id, name, max_amt, percent);
                coupons.add(coupon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return coupons;
    }

    @Override
    public Coupon getCouponById(int id) {
        Coupon coupon = null;

        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "select * from coupons where coupon_id="+id + ";" ;
            ResultSet rs = stmt.executeQuery(query) ;

            while(rs.next())
            {
                int coupon_id = rs.getInt("coupon_id");
                String name = rs.getString("name");
                double max_amt = Double.parseDouble(rs.getString("max_amt"));
                double percent = Double.parseDouble(rs.getString("percent"));

                coupon = new Coupon(coupon_id, name, max_amt, percent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return coupon;
    }
}
