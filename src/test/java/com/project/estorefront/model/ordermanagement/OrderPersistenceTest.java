package com.project.estorefront.model.ordermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class OrderPersistenceTest {

    @Test
    void testGetOrderAndItemDetailsForOrderID() throws SQLException {
        IOrderPersistence orderPersistence = new OrderPersistenceMock();
        OrderDetails orderDetail = orderPersistence.loadOrderAndItems("OR12365");
        assertEquals(orderDetail.getOrderID(), "OR12365");
    }

    @Test
    void testGetOrderAndItemDetailsForNotPresentOrderID() throws SQLException {
        IOrderPersistence orderPersistence = new OrderPersistenceMock();
        OrderDetails orderDetail = orderPersistence.loadOrderAndItems("OR1238");
        assertNull(orderDetail);
    }

    @Test
    void testGetOrderAndItemDetailsForEmptyOrderID() throws SQLException {
        IOrderPersistence orderPersistence = new OrderPersistenceMock();
        OrderDetails orderDetail = orderPersistence.loadOrderAndItems("");
        assertNull(orderDetail);
    }
}