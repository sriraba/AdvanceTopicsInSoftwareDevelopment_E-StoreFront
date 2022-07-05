package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellerOrderPersistence implements ISellerOrderPersistence{
    private Connection connection;

    @Override
    public ArrayList<OrderDetails> load(String userID) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        ArrayList<OrderDetails> sellerOrderDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM buyer_orders WHERE buyer_orders.seller_id = ?");
            preparedStatement.setString(1, userID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setOrderID(rs.getString("order_id"));
                orderDetail.setOrderStatus(rs.getString("order_status"));
                orderDetail.setTotalAmount(rs.getFloat("total_amount"));
                orderDetail.setCouponApplied(rs.getString("is_coupon_applied"));
                orderDetail.setDeliveryCharges(rs.getString("delivery_charges"));
                orderDetail.setDeliveryAddress(rs.getString("delivery_address"));
                orderDetail.setCouponAmount(rs.getFloat("coupon_amt"));
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
