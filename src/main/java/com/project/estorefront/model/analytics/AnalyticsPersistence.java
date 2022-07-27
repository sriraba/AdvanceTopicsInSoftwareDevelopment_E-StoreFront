package com.project.estorefront.model.analytics;

import com.project.estorefront.model.database.DatabaseFactory;
import com.project.estorefront.model.database.IDatabase;

import java.sql.*;

public class AnalyticsPersistence implements IAnalyticsPersistence {

    private IDatabase database = DatabaseFactory.instance().makeDatabase();
    private Connection connection = null;
    private ResultSet rs = null;

    @Override
    public int getTotalOrders(String userID) {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS total_orders from buyer_orders where user_id = ?";

        PreparedStatement statement = null;
        try {
            connection = database.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            rs = statement.executeQuery();

            if (rs.next()) {
                totalOrders = rs.getInt("total_orders");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return totalOrders;
    }

    @Override
    public int getTotalSales(String userID) {
        int totalSales = 0;
        String query = "SELECT SUM(total_amount) AS total_sales from buyer_orders WHERE user_id = ?";

        PreparedStatement statement = null;
        try {
            connection = database.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            rs = statement.executeQuery();

            if (rs.next()) {
                totalSales = rs.getInt("total_sales");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return totalSales;
    }

    @Override
    public int getTotalReturningBuyers(String userID) {
        int totalReturningCustomers = 0;
        String query = "SELECT user_id, COUNT(*) FROM buyer_orders GROUP BY user_id HAVING COUNT(*) > 1 WHERE user_id = ?";

        PreparedStatement statement = null;
        try {
            connection = database.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            rs = statement.executeQuery();

            while (rs.next()) {
                totalReturningCustomers += 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return totalReturningCustomers;
    }

}
