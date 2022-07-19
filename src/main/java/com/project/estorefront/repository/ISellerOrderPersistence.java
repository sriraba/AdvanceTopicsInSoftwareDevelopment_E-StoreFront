package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public interface ISellerOrderPersistence extends IOrderPersistence{
    public ArrayList<OrderDetails> loadOrders(String userID);
    public void updateDeliveryPerson(String orderID, String name);
}
