package com.project.estorefront.model;

import com.project.estorefront.repository.IOrderPersistence;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

public class OrderAndItemsFactoryTests {

    @Test
    public void testOrderAndItemsFactoryInstance() {
        assertNotNull(OrderAndItemsFactory.instance());
    }

    @Test
    public void testMakeOrderPersistence() {
        IOrderPersistence orderPersistence = OrderAndItemsFactory.instance().makeOrderPersistence();
        assertNotNull(orderPersistence);
    }
}
