package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;

import java.util.ArrayList;

public interface ISeller {

    String getBusinessName();

    void setBusinessDescription(String businessDescription);

    String getBusinessDescription();

    void setBusinessName(String businessName);
    boolean updateProfile(ISellerPersistence persistence);
    boolean deleteProfile(ISellerPersistence persistence);

}
