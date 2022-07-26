package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISellerOrderPersistence extends IOrderPersistence {
    ArrayList<OrderDetails> loadOrders(String userID) throws SQLException;

    IPropertiesReader.PersistenceStatus updateDeliveryCharges(String orderID, String name) throws SQLException;
    IPropertiesReader.PersistenceStatus updateOrderStatus(String orderID);
}
