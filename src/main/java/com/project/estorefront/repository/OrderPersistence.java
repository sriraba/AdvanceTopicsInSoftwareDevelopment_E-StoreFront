package com.project.estorefront.repository;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.ItemDetails;
import com.project.estorefront.model.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPersistence implements IOrderPersistence {
    @Override
    public OrderDetails loadOrderAndItems(String orderID) {
        PreparedStatement preparedStatement;

        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();
        OrderDetails orderDetail = new OrderDetails();

        ArrayList<com.project.estorefront.model.ItemDetails> sellerItemDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM buyer_orders JOIN order_items ON buyer_orders.order_id = order_items.order_id AND buyer_orders.order_id = ?");
            preparedStatement.setString(1, orderID);
            ResultSet rs = preparedStatement.executeQuery();
            com.project.estorefront.model.ItemDetails itemDetail = new ItemDetails();
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
        } catch (SQLException e) {
            e.printStackTrace();
            return orderDetail;
        } finally {
            database.closeConnection();
        }
    }
}
