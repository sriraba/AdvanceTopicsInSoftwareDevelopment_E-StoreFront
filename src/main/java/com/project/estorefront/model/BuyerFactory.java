package com.project.estorefront.model;

import com.project.estorefront.repository.*;

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
    public IBuyerPersistence makeBuyerPersistence() {
        return new BuyerPersistence();
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
