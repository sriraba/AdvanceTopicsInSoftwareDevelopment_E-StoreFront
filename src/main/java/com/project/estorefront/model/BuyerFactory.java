package com.project.estorefront.model;

import com.project.estorefront.repository.BuyerOrderPersistence;
import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.ISellerOrderPersistence;
import com.project.estorefront.repository.SellerOrderPersistence;

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
    public User makeBuyer() {
        return new Buyer();
    }

    @Override
    public IBuyerOrderPersistence makeBuyerOrderPersistence() {
        return new BuyerOrderPersistence();
    }

}
