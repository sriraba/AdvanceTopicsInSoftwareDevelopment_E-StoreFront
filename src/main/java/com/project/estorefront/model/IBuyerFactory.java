package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerOrderPersistence;

public interface IBuyerFactory {

    User makeBuyer();
    IBuyerOrderPersistence makeBuyerOrderPersistence();

}
