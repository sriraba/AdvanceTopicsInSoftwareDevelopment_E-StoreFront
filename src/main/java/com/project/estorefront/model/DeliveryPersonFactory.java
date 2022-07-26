package com.project.estorefront.model;

import com.project.estorefront.repository.DeliveryPersonPersistence;
import com.project.estorefront.repository.IDatabase;
import com.project.estorefront.repository.IDeliveryPersonPersistence;

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
}
