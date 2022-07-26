package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBuyerOrderPersistence {
    ArrayList<OrderDetails> loadOrders(String userID) throws SQLException;

    IPropertiesReader.PersistenceStatus submitReview(String userID, String orderID, String description) throws SQLException;;
}
