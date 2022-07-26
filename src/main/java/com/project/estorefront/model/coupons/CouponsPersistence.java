package com.project.estorefront.model.coupons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.estorefront.model.database.IDatabase;

public class CouponsPersistence implements ICouponsPersistence {

    private IDatabase database;

    public CouponsPersistence(IDatabase database) {
        this.database = database;
    }

    @Override
    public void saveCoupon(ICoupon coupon) throws SQLException {
        Connection connection = database.getConnection();

        try {
            String query = "insert into coupons values (?,?,?,?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, coupon.getCouponID());
            preparedStmt.setInt(2, 1);
            preparedStmt.setString(3, coupon.getName());
            preparedStmt.setString(4, Double.toString(coupon.getMaxAmount()));
            preparedStmt.setString(5, Double.toString(coupon.getPercent()));
            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    @Override
    public void updateCoupon(ICoupon coupon) throws SQLException {
        Connection connection = database.getConnection();

        try {
            String query = "update coupons set name=?, max_amt=?, percent=? where coupon_id=?";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, coupon.getName());
            preparedStmt.setString(2, Double.toString(coupon.getMaxAmount()));
            preparedStmt.setString(3, Double.toString(coupon.getPercent()));
            preparedStmt.setInt(4, coupon.getCouponID());

            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }

    @Override
    public List<ICoupon> getCoupons() throws SQLException {
        Connection connection = database.getConnection();

        Statement stmt;
        ArrayList<ICoupon> coupons = new ArrayList<>();
        try {

            stmt = connection.createStatement();

            String query = "select * from coupons;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int coupon_id = rs.getInt("coupon_id");
                String name = rs.getString("name");
                double max_amt = Double.parseDouble(rs.getString("max_amt"));
                double percent = Double.parseDouble(rs.getString("percent"));

                ICoupon coupon = new Coupon(coupon_id, name, max_amt, percent);
                coupons.add(coupon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return coupons;
    }

    @Override
    public Coupon getCouponById(int id) throws SQLException {
        Connection connection = database.getConnection();

        Coupon coupon = null;
        Statement stmt;
        try {

            stmt = connection.createStatement();

            String query = "select * from coupons where coupon_id=" + id + ";";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int coupon_id = rs.getInt("coupon_id");
                String name = rs.getString("name");
                double max_amt = Double.parseDouble(rs.getString("max_amt"));
                double percent = Double.parseDouble(rs.getString("percent"));

                coupon = new Coupon(coupon_id, name, max_amt, percent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return coupon;
    }

    public void deleteCoupon(int id) throws SQLException {
        Connection connection = database.getConnection();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "delete from coupons where coupon_id=" + id + ";";
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

    }
}
