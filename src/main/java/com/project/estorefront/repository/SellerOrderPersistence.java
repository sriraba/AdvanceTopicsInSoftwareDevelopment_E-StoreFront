package com.project.estorefront.repository;

import com.project.estorefront.model.ItemDetails;
import com.project.estorefront.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellerOrderPersistence implements ISellerOrderPersistence{
    private Connection connection;

    @Override
    public ArrayList<OrderDetails> loadOrders(String userID) {
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
    @Override
    public OrderDetails loadOrderAndItems(String orderID) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        OrderDetails orderDetail = new OrderDetails();
        ArrayList<ItemDetails> sellerItemDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM buyer_orders JOIN order_items ON buyer_orders.order_id = order_items.order_id AND buyer_orders.order_id = ?");
            preparedStatement.setString(1, orderID);
            ResultSet rs = preparedStatement.executeQuery();
            ItemDetails itemDetail = new ItemDetails();
            while (rs.next()) {
                orderDetail.setOrderID(rs.getString("order_id"));
                orderDetail.setOrderStatus(rs.getString("order_status"));
                orderDetail.setTotalAmount(rs.getFloat("total_amount"));
                orderDetail.setCouponApplied(rs.getString("is_coupon_applied"));
                orderDetail.setDeliveryCharges(rs.getString("delivery_charges"));
                orderDetail.setDeliveryAddress(rs.getString("delivery_address"));
                orderDetail.setCouponAmount(rs.getFloat("coupon_amt"));
                orderDetail.setPincode(rs.getString("pincode"));
                itemDetail.setItemID(rs.getString("item_id"));
                itemDetail.setQuantity(rs.getInt("quantity"));
                sellerItemDetails.add(itemDetail);
            }
            orderDetail.setItemDetails(sellerItemDetails);
            return orderDetail;
        }catch (SQLException e) {
            e.printStackTrace();
            return orderDetail;
        }
    }

}
