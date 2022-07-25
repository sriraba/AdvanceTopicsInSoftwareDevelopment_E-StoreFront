package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerOrderPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.SellerOrderPersistence;
import com.project.estorefront.repository.SellerPersistence;

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
    public ISellerPersistence makeSellerPersistence() {
        return new SellerPersistence();
    }



    @Override
    public ISellerOrderManagement makeSellerOrderManagement() {
        return new OrderDetails();
    }

    @Override
    public ISellerOrderPersistence makeSellerOrderPersistence() {
        return new SellerOrderPersistence();
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
