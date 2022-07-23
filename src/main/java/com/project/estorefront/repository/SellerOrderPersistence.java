package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.IDeliveryPerson;
import com.project.estorefront.model.OrderDetails;

public class SellerOrderPersistence extends OrderPersistence implements ISellerOrderPersistence {
    private Connection connection;

    @Override
    public ArrayList<OrderDetails> loadOrders(String sellerID) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        ArrayList<OrderDetails> sellerOrderDetails = new ArrayList<>();
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM buyer_orders WHERE buyer_orders.seller_id = ?");
            preparedStatement.setString(1, sellerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderID(rs.getString("order_id"));
                orderDetail.setOrderStatus(rs.getString("order_status"));
                orderDetail.setCouponID(rs.getString("coupon_id"));
                orderDetail.setTotalAmount(rs.getFloat("total_amount"));
                orderDetail.setDeliveryCharges(rs.getString("delivery_charges"));
                orderDetail.setDeliveryAddress(rs.getString("delivery_address"));
                orderDetail.setPincode(rs.getString("pincode"));
                orderDetail.setBuyerID(rs.getString("user_id"));
                orderDetail.setSellerID(rs.getString("seller_id"));
                sellerOrderDetails.add(orderDetail);
            }
            return sellerOrderDetails;
        } catch (SQLException e) {
            e.printStackTrace();
            return sellerOrderDetails;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public void updateDeliveryPerson(String orderID, String charge) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        ArrayList<IDeliveryPerson> deliveryPersonDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE buyer_orders WHERE buyer_orders.order_id = ? AND buyer_orders.delivery_charges = ?");
            preparedStatement.setString(1, orderID);
            preparedStatement.setString(2, charge);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            database.closeConnection();
        }
    }

}
