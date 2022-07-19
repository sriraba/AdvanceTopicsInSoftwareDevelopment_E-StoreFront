package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerOrderPersistence implements IBuyerOrderPersistence{
    @Override
    public ArrayList<OrderDetails> loadOrders(String buyerID) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        ArrayList<OrderDetails> sellerOrderDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM buyer_orders WHERE buyer_orders.user_id = ?");
            preparedStatement.setString(1, buyerID);
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
                sellerOrderDetails.add(orderDetail);
            }
            return sellerOrderDetails;
        }catch (SQLException e) {
            e.printStackTrace();
            return sellerOrderDetails;
        }
    }
}
