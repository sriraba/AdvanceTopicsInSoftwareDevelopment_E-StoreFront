package com.project.estorefront.repository;

import com.project.estorefront.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void testGetSellerByID() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();
        sellerPersistenceMock.addMockSeller();
        User seller = sellerPersistenceMock.getSellerByID("1");
        assertEquals(seller.getUserID(), "1");
    }

    @Test
    void testGetSellerByIDWithInvalidID() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();
        sellerPersistenceMock.addMockSeller();
        User seller = sellerPersistenceMock.getSellerByID("2");
        assertNull(seller);
    }
    @Test
    void testGetSellerByIDWithValidID() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();
        sellerPersistenceMock.addMockSeller();
        User seller = sellerPersistenceMock.getSellerByID("1");
        assertNotNull(seller);
    }

    @Test
    void testGetSellerByCategory() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();
        sellerPersistenceMock.addMockSellerForCategoryTest();
        ArrayList<User> seller = sellerPersistenceMock.getAllSellersByCategory(ItemCategory.GROCERY, "Halifax");
        assertNotNull(seller);
    }

    @Test
    void testGetSellerByCategoryWithNoSellers() {
        SellerPersistenceMock sellerPersistenceMock = new SellerPersistenceMock();
        ArrayList<User> seller = sellerPersistenceMock.getAllSellersByCategory(ItemCategory.GROCERY, "Halifax");
        assertNull(seller);
    }

    @Test
    void testUpdateSellerAccountWhenUserDoesNotExists(){
        SellerPersistenceMock persistence =  new SellerPersistenceMock();
        persistence.addMockSeller();

        User seller = new Seller();
        seller.setFirstName("Faiza");
        seller.setLastName("Umatiya");
        seller.setPhone("982193");
        ((Seller)seller).setBusinessName("Meritas Holidays");
        ((Seller) seller).setBusinessDescription("Enjoy your vacation with the exclusive offers");
        seller.setUserID("2");

        assertFalse(persistence.updateSellerAccount(seller));

    }
    @Test
    void testUpdateSellerAccountWhenUserExists(){
        SellerPersistenceMock persistence = new SellerPersistenceMock();
        persistence.addMockSeller();
        User seller = new Seller();
        seller.setFirstName("Faiza");
        seller.setLastName("Umatiya");
        seller.setPhone("98729348");
        ((Seller)seller).setBusinessName("Meritas Holidays");
        ((Seller) seller).setBusinessDescription("Enjoy your vacation with the exclusive offers");
        seller.setUserID("1");

        assertTrue(persistence.updateSellerAccount(seller));
    }
    @Test
    public void testDeactivateSellerAccountWhenUserDoesNotExist() {
        SellerPersistenceMock persistence = new SellerPersistenceMock();
        persistence.addMockSeller();
        User seller = new Seller();
        seller.setFirstName("Faiza");
        seller.setLastName("Umatiya");
        seller.setPhone("8374294");
        ((Seller)seller).setBusinessName("Meritas Holidays");
        ((Seller) seller).setBusinessDescription("Enjoy your vacation with the exclusive offers");
        seller.setUserID("2");
        assertFalse(persistence.deactivateSellerAccount(seller));
    }

    @Test
    public void testDeactivateSellerAccountWhenUserExists() {
        SellerPersistenceMock persistence = new SellerPersistenceMock();
        persistence.addMockSeller();
        User seller = new Seller();
        seller.setFirstName("Faiza");
        seller.setLastName("Umatiya");
        seller.setPhone("8374294");
        ((Seller)seller).setBusinessName("Meritas Holidays");
        ((Seller) seller).setBusinessDescription("Enjoy your vacation with the exclusive offers");
        seller.setUserID("1");
        assertTrue(persistence.deactivateSellerAccount(seller));
    }
}

