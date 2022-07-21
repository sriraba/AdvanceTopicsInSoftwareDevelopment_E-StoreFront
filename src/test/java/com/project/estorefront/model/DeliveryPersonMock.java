package com.project.estorefront.model;


import java.util.ArrayList;

public class DeliveryPersonMock implements IDeliveryPerson{
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
    public String getSellerID() {
        return null;
    }

    @Override
    public void setSellerID(String sellerID) {

    }

    @Override
    public String getPersonName() {
        return null;
    }

    @Override
    public void setPersonName(String personName) {

    }

    @Override
    public ArrayList<IDeliveryPerson> getDeliveryPersonDetails(String sellerID) {
        addMockDeliveryPersonForID(sellerID);
        return (deliveryPeople!= null && deliveryPeople.size()>0) ? deliveryPeople : null;

    }
}
