package com.project.estorefront.model.ordermanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.IPropertiesReader;
import com.project.estorefront.model.database.IDatabase;

public class SellerOrderPersistence extends OrderPersistence implements ISellerOrderPersistence {
    private IDatabase database;

    public SellerOrderPersistence(IDatabase database) {
        super(database);
        this.database = database;
    }

    @Override
    public ArrayList<OrderDetails> loadOrders(String sellerID) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = database.getConnection();

        ArrayList<OrderDetails> sellerOrderDetails = new ArrayList<>();
        try {
            preparedStatement = connection
                    .prepareStatement("SELECT * FROM buyer_orders WHERE buyer_orders.seller_id = ?");
            preparedStatement.setString(1, sellerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderDetails orderDetail = OrderAndItemsFactory.instance().makeOrderDetails();
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
    public IPropertiesReader.PersistenceStatus updateDeliveryCharges(String orderID, String charge) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = database.getConnection();
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE buyer_orders SET buyer_orders.delivery_charges = ? WHERE buyer_orders.order_id = ? ");
            preparedStatement.setString(1, charge);
            preparedStatement.setString(2, orderID);
            int status = preparedStatement.executeUpdate();
            if (status > 0) {
                return IPropertiesReader.PersistenceStatus.SUCCESS;
            } else {
                return IPropertiesReader.PersistenceStatus.FAILURE;
            }
        } catch (SQLException e) {
            return IPropertiesReader.PersistenceStatus.SQL_EXCEPTION;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public IPropertiesReader.PersistenceStatus updateOrderStatus(String orderID){
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = database.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE buyer_orders SET buyer_orders.order_status = ? WHERE buyer_orders.order_id = ? ");
            preparedStatement.setString(1, "Delivered");
            preparedStatement.setString(2, orderID);
            int status = preparedStatement.executeUpdate();
            if (status > 0) {
                return IPropertiesReader.PersistenceStatus.SUCCESS;
            } else {
                return IPropertiesReader.PersistenceStatus.FAILURE;
            }
        } catch (SQLException e) {
            return IPropertiesReader.PersistenceStatus.SQL_EXCEPTION;
        } finally {
            database.closeConnection();
        }

    }

}
