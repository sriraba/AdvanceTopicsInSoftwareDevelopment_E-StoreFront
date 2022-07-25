package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.IOrderPersistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface IBuyerOrderManagement extends IOrderManagement {
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID, IBuyerOrderPersistence orderPersistence) throws SQLException;

    void submitReview(IBuyerOrderPersistence persistence, String userID, String orderID, String description) throws SQLException;
}
