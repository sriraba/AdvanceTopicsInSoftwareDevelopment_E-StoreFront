package com.project.estorefront.repository;

import com.project.estorefront.model.Coupon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class CouponsPersistence implements ICouponsPersistence{

    Connection connection;

    public CouponsPersistence()
    {
        connection = Database.getConnection();
    }
    @Override
    public void saveCoupon(Coupon coupon) {

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
        return null;
    }
}
