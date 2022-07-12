package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class InventoryItemPersistenceTest {


    @Test
    public void inventorySaveUpdateDeleteTest() throws SQLException {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        IInventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setUserID("1");
        inventoryItem.setItemCategory(ItemCategory.GROCERY);
        inventoryItem.setItemName("abc");
        inventoryItem.setItemDescription("abc");
        inventoryItem.setItemPrice(8.0);
        inventoryItem.setItemQuantity(2);

        boolean saved = inventoryItemPersistence.save(inventoryItem);

        inventoryItem.setItemName("xyz");
        boolean updated = inventoryItemPersistence.update(inventoryItem);

        boolean deleted = inventoryItemPersistence.delete(inventoryItem);

        assertTrue(saved && deleted && updated);
    }

    @Test
    public void getAllInventoryItemsTest() throws SQLException {
        IInventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
        ArrayList<IInventoryItem> all = inventoryItemPersistence.getAll("1");
        assertNotNull(all);
    }

}
