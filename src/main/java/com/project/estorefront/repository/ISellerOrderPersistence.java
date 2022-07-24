package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public interface ISellerOrderPersistence extends IOrderPersistence{
    ArrayList<OrderDetails> loadOrders(String userID);
    void updateDeliveryPerson(String orderID, String name);
}
