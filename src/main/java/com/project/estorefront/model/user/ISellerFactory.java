package com.project.estorefront.model.user;

import com.project.estorefront.model.ordermanagement.ISellerOrderManagement;
import com.project.estorefront.model.ordermanagement.IDeliveryPerson;
import com.project.estorefront.model.database.IDatabase;
import com.project.estorefront.model.ordermanagement.ISellerOrderPersistence;

public interface ISellerFactory {

    ISellerPersistence makeSellerPersistence(IDatabase database);

    ISellerOrderManagement makeSellerOrderManagement();

    ISellerOrderPersistence makeSellerOrderPersistence(IDatabase database);

    User makeSeller();

    User makeSeller(String sellerID);
    IDeliveryPerson makeDeliveryPerson();

}
