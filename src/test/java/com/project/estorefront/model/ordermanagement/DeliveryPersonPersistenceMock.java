package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.ordermanagement.DeliveryPerson;
import com.project.estorefront.model.ordermanagement.IDeliveryPerson;
import com.project.estorefront.model.ordermanagement.IDeliveryPersonPersistence;

import java.util.ArrayList;

public class DeliveryPersonPersistenceMock implements IDeliveryPersonPersistence {

    ArrayList<IDeliveryPerson> deliveryPeople = new ArrayList<>();
    private ArrayList<IDeliveryPerson> addMockDeliveryPerson() {
        ArrayList<IDeliveryPerson> deliveryPersons = new ArrayList<>();
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setPersonName("Francis");
        deliveryPerson.setSellerID("5");
        DeliveryPerson deliveryPerson1 = new DeliveryPerson();
        deliveryPerson1.setPersonName("Rohny");
        deliveryPerson1.setSellerID("5");
        DeliveryPerson deliveryPerson2 = new DeliveryPerson();
        deliveryPerson2.setPersonName("Sam");
        deliveryPerson2.setSellerID("6");
        deliveryPersons.add(deliveryPerson);
        deliveryPersons.add(deliveryPerson1);
        deliveryPersons.add(deliveryPerson2);
        return deliveryPersons;
    }

    private void addMockDeliveryPersonForID(String sellerID){

        if(sellerID.isEmpty() || sellerID == null || addMockDeliveryPerson() == null){
            deliveryPeople = null;
        }
        else{
            for (IDeliveryPerson mockDeliveryPerson : addMockDeliveryPerson()) {
                if(sellerID.equalsIgnoreCase(mockDeliveryPerson.getSellerID())){
                    deliveryPeople.add(mockDeliveryPerson);
                }
            }
        }
    }

    @Override
    public ArrayList<IDeliveryPerson> getAll(String sellerID) {
        addMockDeliveryPersonForID(sellerID);
        return (deliveryPeople!= null && deliveryPeople.size()>0) ? deliveryPeople : null;
    }
}
