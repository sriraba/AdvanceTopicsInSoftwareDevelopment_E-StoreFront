package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISellerOrderPersistence extends IOrderPersistence {
    ArrayList<OrderDetails> loadOrders(String userID) throws SQLException;

    PersistenceStatus updateDeliveryCharges(String orderID, String name) throws SQLException;
}
