package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.IBuyerPersistence;
import com.project.estorefront.repository.IDatabase;

public interface IBuyerFactory {
    IBuyerPersistence makeBuyerPersistence(IDatabase database);

    User makeBuyer();

    User makeBuyer(String buyerID);

    IBuyerOrderPersistence makeBuyerOrderPersistence(IDatabase database);

    IBuyerOrderManagement makeBuyerOrderManagement();
}
