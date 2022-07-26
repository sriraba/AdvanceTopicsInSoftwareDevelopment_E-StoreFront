package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;
import com.project.estorefront.model.ordermanagement.IOrderManagement;
import com.project.estorefront.model.ordermanagement.OrderDetails;
import com.project.estorefront.model.ordermanagement.ISellerOrderPersistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface ISellerOrderManagement extends IOrderManagement {
    Map<String, ArrayList<OrderDetails>> getSellerOrders(String userID, ISellerOrderPersistence orderPersistence) throws SQLException;
    IPropertiesReader.PersistenceStatus updateOrderStatus(String OrderID, ISellerOrderPersistence orderPersistence);
}
