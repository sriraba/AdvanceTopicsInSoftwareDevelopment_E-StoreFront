package com.project.estorefront.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.User;

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
    void testUpdateSellerAccountWhenUserExists() {

    }

    @Test
    public void testPersistenceSellerUpdateAccount() {
        // ISellerPersistence sellerPersistence = new SellerPersistenceMock();
        // User seller = new Seller();
        // ArrayList<User> seller = sellerPersistenceMock.getSell
    }
}
