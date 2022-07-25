package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

public interface ISeller {

    String getBusinessName();

    String getBusinessDescription();

    boolean updateSellerAccount(ISellerPersistence persistence);

    boolean deactivateSellerAccount(ISellerPersistence persistence);

    User getSellerByID(ISellerPersistence persistence, String sellerID);

}
