package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public interface IBuyerOrderPersistence {
    public ArrayList<OrderDetails> loadOrders(String userID);
}
