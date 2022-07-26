package com.project.estorefront.model.user;

import static org.junit.Assert.assertNotNull;

import com.project.estorefront.model.ordermanagement.ISellerOrderManagement;
import com.project.estorefront.model.user.SellerFactory;
import com.project.estorefront.model.user.User;
import org.junit.jupiter.api.Test;

public class SellerFactoryTests {

    @Test
    public void testSellerFactoryInstance() {
        assertNotNull(SellerFactory.instance());
    }

    /*
     * @Test
     * public void testMakeSellerPersistence() {
     * ISellerPersistence persistence =
     * SellerFactory.instance().makeSellerPersistence();
     * assertNotNull(persistence);
     * }
     */

    @Test
    public void testMakeSellerOrderManagement() {
        ISellerOrderManagement orderManagement = SellerFactory.instance().makeSellerOrderManagement();
        assertNotNull(orderManagement);
    }

    /*
     * @Test
     * public void testMakeSellerOrderPersistence() {
     * ISellerOrderPersistence orderPersistence =
     * SellerFactory.instance().makeSellerOrderPersistence(new Database());
     * assertNotNull(orderPersistence);
     * }
     */

    @Test
    public void testMakeSeller() {
        User seller = SellerFactory.instance().makeSeller();
        assertNotNull(seller);
    }

}