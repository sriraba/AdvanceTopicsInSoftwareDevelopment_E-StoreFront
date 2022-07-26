package com.project.estorefront.model;

import com.project.estorefront.repository.IOrderPersistence;

import java.sql.SQLException;

public interface IOrderManagement {
    OrderDetails getOrderAndItemDetails(String orderID, IOrderPersistence orderPersistence) throws SQLException;
}
