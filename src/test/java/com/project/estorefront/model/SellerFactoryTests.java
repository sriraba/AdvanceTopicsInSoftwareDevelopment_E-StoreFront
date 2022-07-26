package com.project.estorefront.model;

import static org.junit.Assert.assertNotNull;

import com.project.estorefront.repository.Database;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.project.estorefront.repository.ISellerOrderPersistence;
import com.project.estorefront.repository.ISellerPersistence;

public class SellerFactoryTests {

    @Test
    public void testSellerFactoryInstance() {
        assertNotNull(SellerFactory.instance());
    }

//    @Test
//    public void testMakeSellerPersistence() {
//        ISellerPersistence persistence = SellerFactory.instance().makeSellerPersistence();
//        assertNotNull(persistence);
//    }

    @Test
    public void testMakeSellerOrderManagement() {
        ISellerOrderManagement orderManagement = SellerFactory.instance().makeSellerOrderManagement();
        assertNotNull(orderManagement);
    }

    @Test
    public void testMakeSellerOrderPersistence() {
        ISellerOrderPersistence orderPersistence = SellerFactory.instance().makeSellerOrderPersistence(new Database());
        assertNotNull(orderPersistence);
    }

    @Test
    public void testMakeSeller() {
        User seller = SellerFactory.instance().makeSeller();
        assertNotNull(seller);
    }

}