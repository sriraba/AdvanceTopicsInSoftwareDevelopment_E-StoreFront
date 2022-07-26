package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.sql.SQLException;

public interface IOrderPersistence {
    OrderDetails loadOrderAndItems(String orderID) throws SQLException;
}
