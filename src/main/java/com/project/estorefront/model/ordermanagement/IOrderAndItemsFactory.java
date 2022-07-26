package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.database.IDatabase;

public interface IOrderAndItemsFactory {
    IOrderPersistence makeOrderPersistence(IDatabase database);
    OrderDetails makeOrderDetails();
}
