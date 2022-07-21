package com.project.estorefront.model;

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
}
