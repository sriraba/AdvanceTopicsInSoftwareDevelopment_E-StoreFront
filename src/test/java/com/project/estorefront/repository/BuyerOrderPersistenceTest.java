package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {
        "SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true",
        "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class BuyerOrderPersistenceTest {
    @Test
    public void testPersistenceSave() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        PersistenceStatus status = persistence.submitReview("1","OR12365","Good service");
        assertSame(status, PersistenceStatus.SUCCESS);
    }

    @Test
    public void testPersistenceSaveForInvalidOrderID() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        PersistenceStatus status = persistence.submitReview("1","OR1236566","Good service");
        assertSame(status, PersistenceStatus.FAILURE);
    }

    @Test
    void testBuyerOrdersForBuyerID() {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = persistence.loadOrders("1");;
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testBuyerOrdersForNullBuyerID() {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = persistence.loadOrders("");;
        assertNull(orderDetails);
    }

    @Test
    void testBuyerOrdersForBuyerIDNotPresent() {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = persistence.loadOrders("9");;
        assertNull(orderDetails);
    }
}
