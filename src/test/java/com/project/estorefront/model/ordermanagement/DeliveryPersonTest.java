package com.project.estorefront.model.ordermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeliveryPersonTest {
    @Test
    void testGetDeliveryPersonDetailsBySellerID() throws SQLException {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople = deliveryPerson.getDeliveryPersonDetails("5",
                deliveryPersonPersistence);
        assertEquals(deliveryPeople.size(), 2);
    }

    @Test
    void testGetDeliveryPersonDetailsByEmptySellerID() throws SQLException {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople = deliveryPerson.getDeliveryPersonDetails("",
                deliveryPersonPersistence);
        assertNull(deliveryPeople);
    }

    @Test
    void testGetDeliveryPersonDetailsByNotPresentSellerID() throws SQLException {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople = deliveryPerson.getDeliveryPersonDetails("15",
                deliveryPersonPersistence);
        assertNull(deliveryPeople);
    }

}