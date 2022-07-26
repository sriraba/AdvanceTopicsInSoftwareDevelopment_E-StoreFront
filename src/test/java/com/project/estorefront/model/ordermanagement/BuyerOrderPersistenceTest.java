package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BuyerOrderPersistenceTest {
    @Test
    public void testPersistenceSave() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        IPropertiesReader.PersistenceStatus status = persistence.submitReview("1","OR12365","Good service");
        assertSame(status, IPropertiesReader.PersistenceStatus.SUCCESS);
    }

    @Test
    public void testPersistenceSaveForInvalidOrderID() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        IPropertiesReader.PersistenceStatus status = persistence.submitReview("1","OR1236566","Good service");
        assertSame(status, IPropertiesReader.PersistenceStatus.FAILURE);
    }

    @Test
    public void testBuyerOrdersForBuyerID() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = persistence.loadOrders("1");;
        assertEquals(2, orderDetails.size());
    }

    @Test
    public void testBuyerOrdersForNullBuyerID() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = persistence.loadOrders("");;
        assertNull(orderDetails);
    }

    @Test
    public void testBuyerOrdersForBuyerIDNotPresent() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = persistence.loadOrders("9");;
        assertNull(orderDetails);
    }
}
