package com.project.estorefront.model.ordermanagement;

import java.sql.SQLException;

public interface IOrderManagement {
    OrderDetails getOrderAndItemDetails(String orderID, IOrderPersistence orderPersistence) throws SQLException;
}
