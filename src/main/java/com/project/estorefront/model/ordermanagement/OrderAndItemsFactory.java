package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.database.IDatabase;

public class OrderAndItemsFactory implements IOrderAndItemsFactory{

    private static OrderAndItemsFactory instance;
    private OrderAndItemsFactory(){

    }

    public static OrderAndItemsFactory instance() {
        if (instance == null) {
            instance = new OrderAndItemsFactory();
        }
        return instance;
    }

    @Override
    public IOrderPersistence makeOrderPersistence(IDatabase database) {
        return new OrderPersistence(database);
    }
    @Override
    public OrderDetails makeOrderDetails() {
        return new OrderDetails();
    }
}
