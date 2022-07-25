package com.project.estorefront.model;

import com.project.estorefront.repository.DeliveryPersonPersistenceMock;
import com.project.estorefront.repository.IDeliveryPersonPersistence;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
class DeliveryPersonTest {
    @Test
    void testGetDeliveryPersonDetailsBySellerID() {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPerson.getDeliveryPersonDetails("5",deliveryPersonPersistence);
        assertEquals(deliveryPeople.size(),2);
    }
    @Test
    void testGetDeliveryPersonDetailsByEmptySellerID() {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPerson.getDeliveryPersonDetails("",deliveryPersonPersistence);
        assertNull(deliveryPeople);
    }
    @Test
    void testGetDeliveryPersonDetailsByNotPresentSellerID() {
        IDeliveryPerson deliveryPerson = new DeliveryPerson();
        IDeliveryPersonPersistence deliveryPersonPersistence = new DeliveryPersonPersistenceMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPerson.getDeliveryPersonDetails("15",deliveryPersonPersistence);
        assertNull(deliveryPeople);
    }



}