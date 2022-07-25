package com.project.estorefront.model;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BuyerFactoryTests {
    @Test
    public void testBuyerFactoryInstance() {
        assertNotNull(BuyerFactory.instance());
    }

    @Test
    public void testMakeBuyer() {
        User buyer = BuyerFactory.instance().makeBuyer();
        assertNotNull(buyer);
    }

}
