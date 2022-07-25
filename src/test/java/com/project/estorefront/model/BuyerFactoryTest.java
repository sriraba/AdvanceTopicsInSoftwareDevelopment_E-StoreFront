package com.project.estorefront.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
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
