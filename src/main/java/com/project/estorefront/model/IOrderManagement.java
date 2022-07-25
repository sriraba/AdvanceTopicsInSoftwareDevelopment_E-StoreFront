package com.project.estorefront.model;

import com.project.estorefront.repository.IOrderPersistence;

public interface IOrderManagement {
    OrderDetails getOrderAndItemDetails(String orderID, IOrderPersistence orderPersistence);
}
