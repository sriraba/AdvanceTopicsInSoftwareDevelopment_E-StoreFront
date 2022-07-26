package com.project.estorefront.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.PersistenceStatus;

public interface IBuyerOrderManagement extends IOrderManagement {
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID, IBuyerOrderPersistence orderPersistence)
            throws SQLException;

    PersistenceStatus submitReview(String userID, String orderID, String description,
            IBuyerOrderPersistence orderPersistence) throws SQLException;
}
