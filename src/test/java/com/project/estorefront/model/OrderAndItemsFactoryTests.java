package com.project.estorefront.model;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class OrderAndItemsFactoryTests {

    @Test
    public void testOrderAndItemsFactoryInstance() {
        assertNotNull(OrderAndItemsFactory.instance());
    }

    /*
     * @Test
     * public void testMakeOrderPersistence() {
     * IOrderPersistence orderPersistence =
     * OrderAndItemsFactory.instance().makeOrderPersistence(new Database());
     * assertNotNull(orderPersistence);
     * }
     */
}
