package com.project.estorefront.model.ordermanagement;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeliveryPersonPersistenceTest {

    @Test
    void testGetDeliveryPersonDetailsBySellerID() throws SQLException {
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople = deliveryPersonPersistence.getAll("5");
        assertEquals(deliveryPeople.size(), 2);
    }

    @Test
    void testGetDeliveryPersonDetailsByEmptySellerID() throws SQLException {
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople = deliveryPersonPersistence.getAll("");
        assertNull(deliveryPeople);
    }

    @Test
    void testGetDeliveryPersonDetailsByNotPresentSellerID() throws SQLException {
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople = deliveryPersonPersistence.getAll("15");
        assertNull(deliveryPeople);
    }
}
