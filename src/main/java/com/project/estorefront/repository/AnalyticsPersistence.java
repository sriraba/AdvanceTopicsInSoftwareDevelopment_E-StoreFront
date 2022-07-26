package com.project.estorefront.repository;

import com.project.estorefront.model.DatabaseFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnalyticsPersistence implements IAnalyticsPersistence{

    private IDatabase database = DatabaseFactory.instance().makeDatabase();
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;

    @Override
    public int getTotalOrders() {
        int totalOrders = 0;
        String query = "SELECT COUNT(*) AS total_orders from buyer_orders";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            if(rs.next())
            {
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
    public int getTotalSales() {
        int totalSales = 0;
        String query = "SELECT SUM(total_amount) AS total_sales from buyer_orders";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            if(rs.next())
            {
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
    public int getTotalReturningBuyers() {
        int totalReturningCustomers = 0;
        String query = "SELECT user_id, COUNT(*) FROM buyer_orders GROUP BY user_id\n" +
                " HAVING COUNT(*) > 1";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next())
            {
                totalReturningCustomers += 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        return totalReturningCustomers;
    }

    @Override
    public int getNewBuyers() {
        return 0;
    }
}
