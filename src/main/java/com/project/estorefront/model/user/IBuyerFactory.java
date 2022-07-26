package com.project.estorefront.model.user;

import com.project.estorefront.model.ordermanagement.IBuyerOrderManagement;
import com.project.estorefront.model.ordermanagement.IBuyerOrderPersistence;
import com.project.estorefront.model.database.IDatabase;

public interface IBuyerFactory {
    IBuyerPersistence makeBuyerPersistence(IDatabase database);

    User makeBuyer();

    User makeBuyer(String buyerID);

    IBuyerOrderPersistence makeBuyerOrderPersistence(IDatabase database);

    IBuyerOrderManagement makeBuyerOrderManagement();
}
