package com.project.estorefront.repository;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.OrderDetails;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class BuyerOrderPersistence extends OrderPersistence implements IBuyerOrderPersistence {
    @Override
    public ArrayList<OrderDetails> loadOrders(String buyerID) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

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
                orderDetail.setBuyerID(rs.getString("user_id"));
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
    public void submitReview(String userID, String orderID, String description) {
        PreparedStatement preparedStatement = null;
        Database database = (Database) DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();
        try{

            String persistReview = "insert into reviews (review_id, description, user_id, order_id ) " +
                    "values (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(persistReview);

            String reviewID = UUID.randomUUID().toString();
            preparedStatement.setString(1, reviewID);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, userID);
            preparedStatement.setString(4, orderID);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
