package com.project.estorefront.model.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.inventory.ItemCategory;
import org.junit.jupiter.api.Test;

public class SellerTests {

    @Test
    public void testSeller() {
        Seller seller = new Seller();
        seller.setBusinessName("BNAME");
        seller.setBusinessDescription("DESC");
        assertNotNull(seller);
    }

    @Test
    public void testGetAllSellers() throws SQLException {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ((SellerPersistenceMock) persistence).addMockSellerWithCity("Halifax");
        ArrayList<User> sellers = Seller.getAllSellersByCity(persistence, "Halifax");
        assert (sellers != null && sellers.size() > 0);
    }

    @Test
    public void testGetAllSellersWithNoSellers() throws SQLException {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ArrayList<User> sellers = Seller.getAllSellersByCity(persistence, "Halifax");
        assert (sellers == null);
    }

    @Test
    public void testGetAllSellersByCategory() throws SQLException {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ((SellerPersistenceMock) persistence).addMockSellerForCategoryTest();
        ArrayList<User> sellers = Seller.getAllSellersByCategory(persistence, ItemCategory.GROCERY, "Halifax");
        assert (sellers != null && sellers.size() > 0);
    }

    @Test
    public void testGetAllSellersByCategoryWithNoSellers() throws SQLException {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ArrayList<User> sellers = Seller.getAllSellersByCategory(persistence, ItemCategory.GROCERY, "Halifax");
        assert (sellers == null);
    }

}
