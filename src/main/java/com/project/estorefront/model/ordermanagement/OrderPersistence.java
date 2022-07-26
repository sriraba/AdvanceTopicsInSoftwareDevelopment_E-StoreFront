package com.project.estorefront.model.ordermanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.database.IDatabase;

public class OrderPersistence implements IOrderPersistence {

    private IDatabase database;

    public OrderPersistence(IDatabase database) {
        this.database = database;
    }

    public OrderPersistence() {

    }

    @Override
    public OrderDetails loadOrderAndItems(String orderID) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = database.getConnection();
        OrderDetails orderDetail = OrderAndItemsFactory.instance().makeOrderDetails();
        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM buyer_orders JOIN order_items ON buyer_orders.order_id = order_items.order_id AND buyer_orders.order_id = ?");
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
                orderDetail.setBuyerID(rs.getString("user_id"));
                itemDetails.add(itemDetail);
            }
            orderDetail.setItemDetails(itemDetails);
            return orderDetail;
        } catch (SQLException e) {
            e.printStackTrace();
            return orderDetail;
        } finally {
            database.closeConnection();
        }
    }
}
