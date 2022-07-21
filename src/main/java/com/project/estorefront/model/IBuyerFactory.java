package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerPersistence;
import com.project.estorefront.repository.ISellerPersistence;

public interface IBuyerFactory {
    IBuyerPersistence makeBuyerPersistence();
    User makeBuyer();

}
