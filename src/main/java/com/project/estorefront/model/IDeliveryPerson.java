package com.project.estorefront.model;

import java.util.ArrayList;

public interface IDeliveryPerson {
    public String getSellerID();
    public void setSellerID(String sellerID);
    public String getPersonName();
    public void setPersonName(String personName);
    public ArrayList<IDeliveryPerson> getDeliveryPersonDetails(String sellerID);
}
