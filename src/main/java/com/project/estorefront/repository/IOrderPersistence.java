package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

public interface IOrderPersistence {
    public OrderDetails loadOrderAndItems(String orderID);
}
