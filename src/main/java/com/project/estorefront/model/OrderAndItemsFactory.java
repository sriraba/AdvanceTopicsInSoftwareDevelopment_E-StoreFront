package com.project.estorefront.model;

import com.project.estorefront.repository.IOrderPersistence;
import com.project.estorefront.repository.OrderPersistence;

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
    public IOrderPersistence makeOrderPersistence() {
        return new OrderPersistence();
    }
}
