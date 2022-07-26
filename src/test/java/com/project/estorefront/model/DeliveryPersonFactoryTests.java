package com.project.estorefront.model;

import com.project.estorefront.repository.IDeliveryPersonPersistence;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DeliveryPersonFactoryTests {

    @Test
    public void testDeliveryPersonFactoryInstance() {
        assertNotNull(DeliveryPersonFactory.instance());
    }

    /*@Test
    public void testMakeBuyerPersistence() {
        IDeliveryPersonPersistence persistence = DeliveryPersonFactory.instance().makeDeliveryPersonPersistence();
        assertNotNull(persistence);
    }*/
}
