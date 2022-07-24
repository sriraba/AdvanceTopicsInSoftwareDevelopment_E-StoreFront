package com.project.estorefront.repository;

import com.project.estorefront.model.IDeliveryPerson;

import java.util.ArrayList;

public interface IDeliveryPersonPersistence {
    ArrayList<IDeliveryPerson> getAll(String sellerID);
}
