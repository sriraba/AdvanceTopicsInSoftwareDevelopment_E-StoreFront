package com.project.estorefront.repository;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.ICart;
import com.project.estorefront.model.IInventoryItem;

import java.sql.*;

public class PlaceOrderPersistence implements IPlaceOrderPersistence{
    @Override
    public boolean placeOrder(ICart cart, String buyerID, String address, String pincode) {
        boolean res = true;
        String newOrderID = getNewOrderID();
        PreparedStatement preparedStatement;

        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            String query = "INSERT INTO buyer_orders VALUES (?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newOrderID);
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, "Placed");
            preparedStatement.setDouble(4, cart.getTotal());
            preparedStatement.setString(5, "");
            preparedStatement.setInt(6, 0);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, pincode);
            preparedStatement.setString(9, cart.getCartItems().get(0).getUserID());
            preparedStatement.execute();

            for(IInventoryItem item : cart.getCartItems())
            {
                query = "INSERT INTO order_items VALUES (?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newOrderID);
                preparedStatement.setString(2, item.getItemID());
                preparedStatement.setInt(3, item.getItemQuantity());
                preparedStatement.setDouble(4, item.getItemPrice());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            res = false;
        }

        return res;
    }

    private String getNewOrderID()
    {
        String orderID = "";

        Statement statement;

        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT order_id FROM buyer_orders ORDER BY order_id DESC LIMIT 1");

            while (rs.next()) {
                orderID = rs.getString("order_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }

        if(!orderID.matches(""))
        {
            String [] tmp = orderID.trim().split("R");

            orderID = "OR" + (Integer.parseInt(tmp[1]) + 1);
        }
        else
        {
            orderID = "OR0001";
        }

        return orderID;
    }
}
