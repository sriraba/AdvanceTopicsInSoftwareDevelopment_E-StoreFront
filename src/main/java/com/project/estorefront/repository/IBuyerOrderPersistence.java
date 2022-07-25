package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBuyerOrderPersistence {
    ArrayList<OrderDetails> loadOrders(String userID) throws SQLException;

    void submitReview(String userID, String orderID, String description) throws SQLException;
}
