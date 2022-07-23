package com.project.estorefront.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
class OrderDetailsTest {

    @Test
    void testGetSellerOrdersForSellerID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderDetailsMock.getSellerOrders("5");
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testGetSellerOrdersForNullSellerID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderDetailsMock.getSellerOrders("");
        assertNull(orderDetails);
    }

    @Test
    void testGetSellerOrdersForSellerIDNotPresent() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderDetailsMock.getSellerOrders("8");
        assertNull(orderDetails);
    }

    @Test
    void testBuyerOrdersForBuyerID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderDetailsMock.getBuyerOrders("1");
        assertEquals(2, orderDetails.size());
    }

    @Test
    void testBuyerOrdersForNullBuyerID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderDetailsMock.getBuyerOrders("");
        assertNull(orderDetails);
    }

    @Test
    void testBuyerOrdersForBuyerIDNotPresent() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        Map<String, ArrayList<OrderDetails>> orderDetails = orderDetailsMock.getBuyerOrders("9");
        assertNull(orderDetails);
    }

    @Test
    void submitReview() {
    }

    @Test
    void testGetOrderAndItemDetailsForOrderID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        OrderDetails orderDetail = orderDetailsMock.getOrderAndItemDetails("OR12365");
        assertEquals(orderDetail.getOrderID(), "OR12365");
    }

    @Test
    void testGetOrderAndItemDetailsForNotPresentOrderID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        OrderDetails orderDetail = orderDetailsMock.getOrderAndItemDetails("OR1238");
        assertNull(orderDetail);
    }

    @Test
    void testGetOrderAndItemDetailsForEmptyOrderID() {
        OrderDetailsMock orderDetailsMock = new OrderDetailsMock();
        OrderDetails orderDetail = orderDetailsMock.getOrderAndItemDetails("");
        assertNull(orderDetail);
    }
}