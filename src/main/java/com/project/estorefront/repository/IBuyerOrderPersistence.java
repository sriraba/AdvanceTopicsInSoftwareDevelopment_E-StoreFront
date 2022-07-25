package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public interface IBuyerOrderPersistence {
    ArrayList<OrderDetails> loadOrders(String userID);

    PersistenceStatus submitReview(String userID, String orderID, String description);
}
