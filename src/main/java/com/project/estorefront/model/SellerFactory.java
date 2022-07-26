package com.project.estorefront.model;

import com.project.estorefront.repository.*;

public class SellerFactory implements ISellerFactory {

    private static SellerFactory instance;

    private SellerFactory() {
    }

    public static ISellerFactory instance() {
        if (instance == null) {
            instance = new SellerFactory();
        }
        return instance;
    }

    @Override
    public ISellerPersistence makeSellerPersistence(IDatabase database) {
        return new SellerPersistence(database);
    }

    @Override
    public ISellerOrderManagement makeSellerOrderManagement() {
        return new OrderDetails();
    }

    @Override
    public ISellerOrderPersistence makeSellerOrderPersistence(IDatabase database) {
        return new SellerOrderPersistence(database);
    }

    @Override
    public User makeSeller() {
        return new Seller();
    }

    @Override
    public User makeSeller(String sellerID) {
        return new Seller(sellerID);
    }

}
