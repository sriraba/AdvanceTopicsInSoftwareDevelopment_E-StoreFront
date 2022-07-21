package com.project.estorefront.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BuyerFactoryTest {

    @Test
    void testBuyerFactoryInstance() {
        assertNotNull(BuyerFactory.instance());
    }

    @Test
    void testMakeBuyer() {
        User buyer = BuyerFactory.instance().makeBuyer();
        assertNotNull(buyer);
    }

}
