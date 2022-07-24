package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerPersistence;

public interface IBuyer {
    boolean updateBuyerAccount(IBuyerPersistence persistence);
    boolean deactivateBuyerAccount(IBuyerPersistence persistence);
    User getBuyerByID(IBuyerPersistence persistence ,String buyerID);
}
