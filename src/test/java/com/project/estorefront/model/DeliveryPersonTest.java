package com.project.estorefront.model;

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
        DeliveryPersonMock deliveryPersonMock = new DeliveryPersonMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPersonMock.getDeliveryPersonDetails("5");
        assertEquals(deliveryPeople.size(),2);
    }
    /*@Test
    void testGetDeliveryPersonDetailsByEmptySellerID() {
        DeliveryPersonMock deliveryPersonMock = new DeliveryPersonMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPersonMock.getDeliveryPersonDetails("");
        assertNull(deliveryPeople);
    }
    @Test
    void testGetDeliveryPersonDetailsByNotPresentSellerID() {
        DeliveryPersonMock deliveryPersonMock = new DeliveryPersonMock();
        ArrayList<IDeliveryPerson> deliveryPeople =deliveryPersonMock.getDeliveryPersonDetails("15");
        assertNull(deliveryPeople);
    }*/



}