package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerOrderPersistence;
import com.project.estorefront.repository.ISellerPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class SellerFactoryTests {

    @Test
    public void testSellerFactoryInstance() {
        assertNotNull(SellerFactory.instance());
    }

    @Test
    public void testMakeSellerPersistence() {
        ISellerPersistence persistence = SellerFactory.instance().makeSellerPersistence();
        assertNotNull(persistence);
    }

    @Test
    public void testMakeSellerOrderManagement() {
        ISellerOrderManagement orderManagement = SellerFactory.instance().makeSellerOrderManagement();
        assertNotNull(orderManagement);
    }

    @Test
    public void testMakeSellerOrderPersistence() {
        ISellerOrderPersistence orderPersistence = SellerFactory.instance().makeSellerOrderPersistence();
        assertNotNull(orderPersistence);
    }

    @Test
    public void testMakeSeller() {
        User seller = SellerFactory.instance().makeSeller();
        assertNotNull(seller);
    }

}