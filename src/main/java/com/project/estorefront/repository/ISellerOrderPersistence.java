package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public interface ISellerOrderPersistence {
    public ArrayList<OrderDetails> loadOrders(String userID);
    public OrderDetails loadOrderAndItems(String orderID);
    public void updateDeliveryPerson(String orderID, String name);

}
