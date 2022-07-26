package com.project.estorefront.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

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
