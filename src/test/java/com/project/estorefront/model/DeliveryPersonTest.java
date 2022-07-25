package com.project.estorefront.model;

import com.project.estorefront.repository.DeliveryPersonPersistenceMock;
import com.project.estorefront.repository.IDeliveryPersonPersistence;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryPersonTest {
    @Test
    void testGetDeliveryPersonDetailsBySellerID() throws SQLException {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPerson.getDeliveryPersonDetails("5",deliveryPersonPersistence);
        assertEquals(deliveryPeople.size(),2);
    }
    @Test
    void testGetDeliveryPersonDetailsByEmptySellerID() throws SQLException {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPerson.getDeliveryPersonDetails("",deliveryPersonPersistence);
        assertNull(deliveryPeople);
    }
    @Test
    void testGetDeliveryPersonDetailsByNotPresentSellerID() throws SQLException {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPerson.getDeliveryPersonDetails("15",deliveryPersonPersistence);
        assertNull(deliveryPeople);
    }



}