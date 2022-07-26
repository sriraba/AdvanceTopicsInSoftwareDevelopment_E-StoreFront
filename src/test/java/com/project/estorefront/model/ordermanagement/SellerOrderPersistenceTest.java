package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SellerOrderPersistenceTest {
    @Test
    void testGetSellerOrdersForSellerID() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = orderPersistence.loadOrders("5");
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testGetSellerOrdersForNullSellerID() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = orderPersistence.loadOrders("");
        assertNull(orderDetails);
    }

    @Test
    void testGetSellerOrdersForSellerIDNotPresent() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        ArrayList<OrderDetails> orderDetails = orderPersistence.loadOrders("8");
        assertNull(orderDetails);
    }

    @Test
    public void testUpdateDeliveryChargesForValidOrder() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        IPropertiesReader.PersistenceStatus status = orderPersistence.updateDeliveryCharges("OR12345", "20");
        assertSame(status, IPropertiesReader.PersistenceStatus.SUCCESS);
    }

    @Test
    public void testUpdateDeliveryChargesForInvalidValidOrder() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        IPropertiesReader.PersistenceStatus status = orderPersistence.updateDeliveryCharges("OR1236566", "20");
        assertSame(status, IPropertiesReader.PersistenceStatus.FAILURE);
    }

    @Test
    public void testUpdateOrderStatusForValidOrder() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        IPropertiesReader.PersistenceStatus status = orderPersistence.updateOrderStatus("OR12345");
        assertSame(status, IPropertiesReader.PersistenceStatus.SUCCESS);
    }

    @Test
    public void testUpdateOrderStatusForInvalidValidOrder() throws SQLException {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        IPropertiesReader.PersistenceStatus status = orderPersistence.updateOrderStatus("OR1236566");
        assertSame(status, IPropertiesReader.PersistenceStatus.FAILURE);
    }

}
