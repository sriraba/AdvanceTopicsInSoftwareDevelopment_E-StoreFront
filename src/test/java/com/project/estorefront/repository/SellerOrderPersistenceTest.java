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
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class SellerOrderPersistenceTest {
    @Test
    void testGetSellerOrdersForSellerID() {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = orderPersistence.loadOrders("5");
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testGetSellerOrdersForNullSellerID() {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = orderPersistence.loadOrders("");
        assertNull(orderDetails);
    }

    @Test
    void testGetSellerOrdersForSellerIDNotPresent() {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = orderPersistence.loadOrders("8");
        assertNull(orderDetails);
    }

    @Test
    public void testUpdateDeliveryChargesForValidOrder() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        PersistenceStatus status = orderPersistence.updateDeliveryCharges("OR12345","20");
        assertSame(status, PersistenceStatus.SUCCESS);
    }

    @Test
    public void testUpdateDeliveryChargesForInvalidValidOrder() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        PersistenceStatus status = orderPersistence.updateDeliveryCharges("OR1236566","20");
        assertSame(status, PersistenceStatus.FAILURE);
    }

}
