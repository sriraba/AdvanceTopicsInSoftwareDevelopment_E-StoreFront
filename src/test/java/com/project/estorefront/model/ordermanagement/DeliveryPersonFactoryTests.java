package com.project.estorefront.model.ordermanagement;

import static org.junit.Assert.assertNotNull;

import com.project.estorefront.model.ordermanagement.DeliveryPersonFactory;
import org.junit.Test;

public class DeliveryPersonFactoryTests {

    @Test
    public void testDeliveryPersonFactoryInstance() {
        assertNotNull(DeliveryPersonFactory.instance());
    }

    /*
     * @Test
     * public void testMakeBuyerPersistence() {
     * IDeliveryPersonPersistence persistence =
     * DeliveryPersonFactory.instance().makeDeliveryPersonPersistence();
     * assertNotNull(persistence);
     * }
     */
}
