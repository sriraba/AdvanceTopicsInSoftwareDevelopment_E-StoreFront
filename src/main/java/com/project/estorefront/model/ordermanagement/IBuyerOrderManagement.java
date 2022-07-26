package com.project.estorefront.model.ordermanagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.project.estorefront.model.IPropertiesReader;

public interface IBuyerOrderManagement extends IOrderManagement {
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID, IBuyerOrderPersistence orderPersistence)
            throws SQLException;

    IPropertiesReader.PersistenceStatus submitReview(String userID, String orderID, String description,
                                                     IBuyerOrderPersistence orderPersistence) throws SQLException;
}
