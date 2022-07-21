package com.project.estorefront.model;

public interface IOrderManagement {
    OrderDetails getOrderAndItemDetails(String orderID);
}
