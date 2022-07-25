package com.project.estorefront.model;

import com.project.estorefront.repository.IDatabase;
import com.project.estorefront.repository.ISellerOrderPersistence;
import com.project.estorefront.repository.ISellerPersistence;

public interface ISellerFactory {

    ISellerPersistence makeSellerPersistence(IDatabase database);

    ISellerOrderManagement makeSellerOrderManagement();

    ISellerOrderPersistence makeSellerOrderPersistence();

    User makeSeller();

    User makeSeller(String sellerID);

}
