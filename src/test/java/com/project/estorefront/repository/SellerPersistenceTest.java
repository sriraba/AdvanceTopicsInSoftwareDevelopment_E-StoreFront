package com.project.estorefront.repository;

import com.project.estorefront.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/**
 * Tests for SellerPersistence
 *
 * @author Hrishi Patel
 * @version 1.0
 * @since 14-07-2022
 */

@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
class SellerPersistenceTest {

    @Test
    void testGetAllSellers() throws SQLException {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();
        sellerPersistenceMock.addMockSeller();
        ArrayList<User> sellers = sellerPersistenceMock.getAllSellers();
        assertEquals(1, sellers.size());
    }

    @Test
    void testGetAllSellersWithNoSellers() throws SQLException {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();

        ArrayList<User> sellers = sellerPersistenceMock.getAllSellers();
        assertNull(sellers);
    }

    @Test
    void testGetAllSellersByCity() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();

        sellerPersistenceMock.addMockSellerWithCity("Halifax");
        ArrayList<User> sellers = sellerPersistenceMock.getAllSellersByCity("Halifax");
        assertEquals(1, sellers.size());
    }

    @Test
    void testGetAllSellersByCityWithCityNameWhichDoesNotHaveASeller() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();

        sellerPersistenceMock.addMockSellerWithCity("Halifax");
        ArrayList<User> sellers = sellerPersistenceMock.getAllSellersByCity("Toronto");
        assertNull(sellers);
    }

}
