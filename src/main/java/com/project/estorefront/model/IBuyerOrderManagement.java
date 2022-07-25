package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerOrderPersistence;

import java.util.ArrayList;
import java.util.Map;

public interface IBuyerOrderManagement extends IOrderManagement{
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID, IBuyerOrderPersistence orderPersistence);
    void submitReview(String userID, String orderID, String description);
}
