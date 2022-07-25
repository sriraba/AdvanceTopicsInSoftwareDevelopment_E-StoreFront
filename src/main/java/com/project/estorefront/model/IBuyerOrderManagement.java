package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.PersistenceStatus;

import java.util.ArrayList;
import java.util.Map;

public interface IBuyerOrderManagement extends IOrderManagement{
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID, IBuyerOrderPersistence orderPersistence);
    PersistenceStatus submitReview(String userID, String orderID, String description,IBuyerOrderPersistence orderPersistence);
}
