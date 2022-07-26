package com.project.estorefront.model.user;

import com.project.estorefront.model.database.IDatabase;
import com.project.estorefront.model.ordermanagement.BuyerOrderPersistence;
import com.project.estorefront.model.ordermanagement.IBuyerOrderManagement;
import com.project.estorefront.model.ordermanagement.IBuyerOrderPersistence;
import com.project.estorefront.model.ordermanagement.OrderDetails;

public class BuyerFactory implements IBuyerFactory {

    private static BuyerFactory instance;

    private BuyerFactory() {
    }

    public static BuyerFactory instance() {
        if (instance == null) {
            instance = new BuyerFactory();
        }
        return instance;
    }

    @Override
    public IBuyerPersistence makeBuyerPersistence(IDatabase database) {
        return new BuyerPersistence(database);
    }

    @Override
    public User makeBuyer() {
        return new Buyer();
    }

    @Override
    public User makeBuyer(String buyerID) {
        return new Buyer(buyerID);
    }

    @Override
    public IBuyerOrderPersistence makeBuyerOrderPersistence(IDatabase database) {
        return new BuyerOrderPersistence(database);
    }

    @Override
    public IBuyerOrderManagement makeBuyerOrderManagement() {
        return new OrderDetails();
    }

}
