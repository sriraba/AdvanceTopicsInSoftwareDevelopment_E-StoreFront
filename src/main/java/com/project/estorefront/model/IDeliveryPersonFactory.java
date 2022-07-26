package com.project.estorefront.model;

import com.project.estorefront.repository.IDatabase;
import com.project.estorefront.repository.IDeliveryPersonPersistence;

public interface IDeliveryPersonFactory {
    IDeliveryPersonPersistence makeDeliveryPersonPersistence(IDatabase database);
}
