package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

import java.util.ArrayList;

public interface ISeller {

    String getBusinessName();

    void setBusinessDescription(String businessDescription);

    String getBusinessDescription();

    void setBusinessName(String businessName);
    boolean updateSellerAccount(ISellerPersistence persistence);
    boolean deactivateSellerAccount(ISellerPersistence persistence);
    User getSellerByID(ISellerPersistence persistence ,String sellerID);

}
