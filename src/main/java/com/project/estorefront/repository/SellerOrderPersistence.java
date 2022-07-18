package com.project.estorefront.repository;

import com.project.estorefront.model.IDeliveryPerson;
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
                orderDetail.setDeliveryCharges(rs.getString("delivery_charges"));
                orderDetail.setCouponID(rs.getString("coupon_id"));
                orderDetail.setTotalAmount(rs.getFloat("total_amount"));
                orderDetail.setDeliveryAddress(rs.getString("delivery_address"));
                orderDetail.setPincode(rs.getString("pincode"));
                orderDetail.setSellerID(rs.getString("seller_id"));
                itemDetail.setItemID(rs.getString("item_id"));
                itemDetail.setQuantity(rs.getInt("quantity"));
                itemDetail.setItemPrice(rs.getFloat("item_price"));
                sellerItemDetails.add(itemDetail);
            }
            orderDetail.setItemDetails(sellerItemDetails);
            return orderDetail;
        }catch (SQLException e) {
            e.printStackTrace();
            return orderDetail;
        }
    }

    @Override
    public void updateDeliveryPerson(String orderID, String charge) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        ArrayList<IDeliveryPerson> deliveryPersonDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("UPDATE buyer_orders WHERE buyer_orders.order_id = ? AND buyer_orders.delivery_charges = ?");
            preparedStatement.setString(1, orderID);
            preparedStatement.setString(2, charge);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
