package com.project.estorefront.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;

import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {
        "SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true",
        "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class BuyerOrderPersistenceTest {

    @Test
    public void testPersistenceSave() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        PersistenceStatus status = persistence.submitReview("1","OR12365","Good service");
        assertSame(status, PersistenceStatus.SUCCESS);
    }

    @Test
    public void testPersistenceSaveForWrongOrderID() throws SQLException {
        IBuyerOrderPersistence persistence = new BuyerOrderPersistenceMock();
        PersistenceStatus status = persistence.submitReview("1","OR1236566","Good service");
        assertSame(status, PersistenceStatus.FAILURE);
    }
}
