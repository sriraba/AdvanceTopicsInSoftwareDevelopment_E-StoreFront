package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerPersistence;
import com.project.estorefront.repository.IDeliveryPersonPersistence;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class DeliveryPersonFactoryTests {

    @Test
    public void testDeliveryPersonFactoryInstance() {
        assertNotNull(DeliveryPersonFactory.instance());
    }

    @Test
    public void testMakeBuyerPersistence() {
        IDeliveryPersonPersistence persistence = DeliveryPersonFactory.instance().makeDeliveryPersonPersistence();
        assertNotNull(persistence);
    }
}
