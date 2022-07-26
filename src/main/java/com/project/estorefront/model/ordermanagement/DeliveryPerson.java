package com.project.estorefront.model.ordermanagement;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryPerson implements IDeliveryPerson{
    private String sellerID;

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

    public ArrayList<IDeliveryPerson> getDeliveryPersonDetails(String sellerID, IDeliveryPersonPersistence deliveryPersonPersistence) throws SQLException {
        if((sellerID == null || sellerID.isEmpty())){
            return null;
        }
        ArrayList<IDeliveryPerson> deliveryPersons = deliveryPersonPersistence.getAll(sellerID);
        return (deliveryPersons == null || deliveryPersons.isEmpty()) ? null : deliveryPersons;
    }

}

