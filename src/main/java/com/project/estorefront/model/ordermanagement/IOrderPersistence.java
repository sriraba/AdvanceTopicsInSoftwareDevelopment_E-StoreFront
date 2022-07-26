package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.ordermanagement.OrderDetails;

import java.sql.SQLException;

public interface IOrderPersistence {
    OrderDetails loadOrderAndItems(String orderID) throws SQLException;
}
