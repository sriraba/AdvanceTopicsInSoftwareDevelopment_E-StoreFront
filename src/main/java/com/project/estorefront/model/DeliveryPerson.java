package com.project.estorefront.model;

import com.project.estorefront.repository.DeliveryPersonPersistence;
import com.project.estorefront.repository.IDeliveryPersonPersistence;

import java.util.ArrayList;

public class DeliveryPerson implements IDeliveryPerson{
    private String sellerID;

    /*public DeliveryPerson(String sellerID, String charge, String personName) {
        this.sellerID = sellerID;
        this.charge = charge;
        this.personName = personName;
    }*/

    private String personName;

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public ArrayList<IDeliveryPerson> getDeliveryPersonDetails(String sellerID){
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistence();
        return deliveryPersonPersistence.getAll(sellerID);
    }

    @Override
    public void save(String name) {

    }

}

