package com.project.estorefront.repository;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.Seller;
import com.project.estorefront.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})

public class BuyerPersistenceTest {
    @Test
    void testGetBuyerByID() {
        BuyerPersistenceMock buyerPersistenceMock = new BuyerPersistenceMock();
        buyerPersistenceMock.addMockBuyer();
        User buyer = buyerPersistenceMock.getBuyerByID("1");
        assertEquals(buyer.getUserID(), "1");
    }
    @Test
    void testGetBuyerByIDWithInvalidID() {
        BuyerPersistenceMock buyerPersistenceMock = new BuyerPersistenceMock();
        buyerPersistenceMock.addMockBuyer();
        User buyer = buyerPersistenceMock.getBuyerByID("2");
        assertNull(buyer);
    }
    @Test
    void testGetBuyerByIDWithValidID(){
        BuyerPersistenceMock buyerPersistenceMock = new BuyerPersistenceMock();
        buyerPersistenceMock.addMockBuyer();
        User buyer = buyerPersistenceMock.getBuyerByID("1");
        assertNotNull(buyer);
    }
    @Test
    void testUpdateBuyerAccountWhenUserDoesNotExists(){
        BuyerPersistenceMock persistence =  new BuyerPersistenceMock();
        persistence.addMockBuyer();

        User buyer = new Buyer();
        buyer.setFirstName("Faiza");
        buyer.setLastName("Umatiya");
        buyer.setPhone("982193");
        buyer.setAddress("13 Fleetview Dr");
        buyer.setUserID("2");

        assertFalse(persistence.updateBuyerAccount(buyer));

    }
    @Test
    void testUpdateBuyerAccountWhenUserExists(){

        BuyerPersistenceMock persistence =  new BuyerPersistenceMock();
        persistence.addMockBuyer();
        User buyer = new Buyer();
        buyer.setFirstName("Faiza");
        buyer.setLastName("Umatiya");
        buyer.setAddress("13 Fleetview Dr");
        buyer.setPhone("982193");
        buyer.setUserID("1");

        assertTrue(persistence.updateBuyerAccount(buyer));

    }

    @Test
    void testDeactivateBuyerAccountWhenUserDoesNotExists(){
        BuyerPersistenceMock persistence =  new BuyerPersistenceMock();
        persistence.addMockBuyer();

        User buyer = new Buyer();
        buyer.setFirstName("Faiza");
        buyer.setLastName("Umatiya");
        buyer.setAddress("13 Fleetview Dr");
        buyer.setPhone("982193");
        buyer.setUserID("2");

        assertFalse(persistence.deactivateBuyerAccount(buyer));

    }
    @Test
    void testDeactivateBuyerAccountWhenUserExists(){

        BuyerPersistenceMock persistence =  new BuyerPersistenceMock();
        persistence.addMockBuyer();
        User buyer = new Buyer();
        buyer.setFirstName("Faiza");
        buyer.setLastName("Umatiya");
        buyer.setAddress("13 Fleetview Dr");
        buyer.setPhone("982193");
        buyer.setUserID("1");

        assertTrue(persistence.deactivateBuyerAccount(buyer));

    }
}
