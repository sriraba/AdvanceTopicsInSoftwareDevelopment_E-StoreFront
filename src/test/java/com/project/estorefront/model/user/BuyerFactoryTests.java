package com.project.estorefront.model.user;

import static org.junit.Assert.assertNotNull;

import com.project.estorefront.model.user.BuyerFactory;
import com.project.estorefront.model.user.User;
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
