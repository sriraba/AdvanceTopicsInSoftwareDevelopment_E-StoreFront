package com.project.estorefront.model;

import com.project.estorefront.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;

class OrderDetailsTest {

    @Test
    void testGetSellerOrdersForSellerID() throws SQLException {
        ISellerOrderManagement orderManagement = new OrderDetails();
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderManagement.getSellerOrders("5",orderPersistence);
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testGetSellerOrdersForNullSellerID() throws SQLException {
        ISellerOrderManagement orderManagement = new OrderDetails();
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderManagement.getSellerOrders("",orderPersistence);
        assertNull(orderDetails);
    }

    @Test
    void testGetSellerOrdersForSellerIDNotPresent() throws SQLException {
        ISellerOrderManagement orderManagement = new OrderDetails();
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistenceMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderManagement.getSellerOrders("8", orderPersistence);
        assertNull(orderDetails);
    }

    @Test
    void testBuyerOrdersForBuyerID() throws SQLException {
        IBuyerOrderManagement orderManagement = new OrderDetails();
        IBuyerOrderPersistence buyerOrderPersistence = new BuyerOrderPersistenceMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderManagement.getBuyerOrders("1",buyerOrderPersistence);
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testBuyerOrdersForNullBuyerID() throws SQLException {
        IBuyerOrderManagement orderManagement = new OrderDetails();
        IBuyerOrderPersistence buyerOrderPersistence = new BuyerOrderPersistenceMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderManagement.getBuyerOrders("", buyerOrderPersistence);
        assertNull(orderDetails);
    }

    @Test
    void testBuyerOrdersForBuyerIDNotPresent() throws SQLException {
        IBuyerOrderManagement orderManagement = new OrderDetails();
        IBuyerOrderPersistence buyerOrderPersistence = new BuyerOrderPersistenceMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderManagement.getBuyerOrders("9",buyerOrderPersistence);
        assertNull(orderDetails);
    }

    @Test
    void submitReviewForValidOrder() {
        IBuyerOrderManagement orderManagement = new OrderDetails();
        IBuyerOrderPersistence buyerOrderPersistence = new BuyerOrderPersistenceMock();
        PersistenceStatus status = orderManagement.submitReview("1","OR12365","Good service",buyerOrderPersistence);
        assertSame(status, PersistenceStatus.SUCCESS);
    }

    @Test
    void submitReviewForInValidOrder() {
        IBuyerOrderManagement orderManagement = new OrderDetails();
        IBuyerOrderPersistence buyerOrderPersistence = new BuyerOrderPersistenceMock();
        PersistenceStatus status = orderManagement.submitReview("1","OR1236789","Good service",buyerOrderPersistence);
        assertSame(status, PersistenceStatus.FAILURE);
    }

    @Test
    void testGetOrderAndItemDetailsForOrderID() throws SQLException {
        IOrderManagement orderManagement = new OrderDetails();
        IOrderPersistence orderPersistence = new OrderPersistenceMock();
        OrderDetails orderDetail = orderManagement.getOrderAndItemDetails("OR12365",orderPersistence);
        assertEquals(orderDetail.getOrderID(), "OR12365");
    }

    @Test
    void testGetOrderAndItemDetailsForNotPresentOrderID() throws SQLException {
        IOrderManagement orderManagement = new OrderDetails();
        IOrderPersistence orderPersistence = new OrderPersistenceMock();
        OrderDetails orderDetail = orderManagement.getOrderAndItemDetails("OR1238",orderPersistence);
        assertNull(orderDetail);
    }

    @Test
    void testGetOrderAndItemDetailsForEmptyOrderID() throws SQLException {
        IOrderManagement orderManagement = new OrderDetails();
        IOrderPersistence orderPersistence = new OrderPersistenceMock();
        OrderDetails orderDetail = orderManagement.getOrderAndItemDetails("",orderPersistence);
        assertNull(orderDetail);
    }
}