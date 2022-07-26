package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.database.IDatabase;

public class DeliveryPersonFactory implements IDeliveryPersonFactory{
    private static DeliveryPersonFactory instance;

    private DeliveryPersonFactory(){

    }

    public static DeliveryPersonFactory instance(){
        if (instance == null) {
            instance = new DeliveryPersonFactory();
        }
        return instance;
    }

    @Override
    public IDeliveryPersonPersistence makeDeliveryPersonPersistence(IDatabase database) {
        return new DeliveryPersonPersistence(database);
    }
    @Override
    public IDeliveryPerson makeDeliveryPerson() {
        return new DeliveryPerson();
    }
}
