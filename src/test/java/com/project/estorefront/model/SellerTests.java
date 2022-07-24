package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerPersistence;
import com.project.estorefront.repository.SellerPersistenceMock;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SellerTests {

    @Test
    public void testSeller() {
        Seller seller = new Seller();
        seller.setBusinessName("BNAME");
        seller.setBusinessDescription("DESC");
        assertNotNull(seller);
    }

    @Test
    public void testGetAllSellers() {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ((SellerPersistenceMock) persistence).addMockSellerWithCity("Halifax");
        ArrayList<User> sellers = Seller.getAllSellersByCity(persistence, "Halifax");
        assert(sellers != null && sellers.size() > 0);
    }

    @Test
    public void testGetAllSellersWithNoSellers() {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ArrayList<User> sellers = Seller.getAllSellersByCity(persistence, "Halifax");
        assert(sellers == null);
    }

    @Test
    public void testGetAllSellersByCategory() {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ((SellerPersistenceMock) persistence).addMockSellerForCategoryTest();
        ArrayList<User> sellers = Seller.getAllSellersByCategory(persistence, ItemCategory.GROCERY, "Halifax");
        assert(sellers != null && sellers.size() > 0);
    }

    @Test
    public void testGetAllSellersByCategoryWithNoSellers() {
        ISellerPersistence persistence = new SellerPersistenceMock();
        ArrayList<User> sellers = Seller.getAllSellersByCategory(persistence, ItemCategory.GROCERY, "Halifax");
        assert(sellers == null);
    }

}
