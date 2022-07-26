package com.project.estorefront.model;

import com.project.estorefront.repository.IDatabase;
import com.project.estorefront.repository.IOrderPersistence;

public interface IOrderAndItemsFactory {
    IOrderPersistence makeOrderPersistence(IDatabase database);
    OrderDetails makeOrderDetails();
}
