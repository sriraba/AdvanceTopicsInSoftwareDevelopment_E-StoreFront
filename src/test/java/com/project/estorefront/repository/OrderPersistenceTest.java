package com.project.estorefront.repository;

import com.project.estorefront.model.IOrderManagement;
import com.project.estorefront.model.OrderDetails;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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