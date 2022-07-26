package com.project.estorefront.model.ordermanagement;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDeliveryPerson {
    String getSellerID();

    void setSellerID(String sellerID);

    String getPersonName();

    void setPersonName(String personName);

    ArrayList<IDeliveryPerson> getDeliveryPersonDetails(String sellerID, IDeliveryPersonPersistence deliveryPersonPersistence) throws SQLException;
}
