package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.database.IDatabase;

public interface IDeliveryPersonFactory {
    IDeliveryPersonPersistence makeDeliveryPersonPersistence(IDatabase database);
    IDeliveryPerson makeDeliveryPerson();
}
