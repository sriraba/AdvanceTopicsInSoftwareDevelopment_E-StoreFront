package com.project.estorefront.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class BuyerFactoryTest {

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
