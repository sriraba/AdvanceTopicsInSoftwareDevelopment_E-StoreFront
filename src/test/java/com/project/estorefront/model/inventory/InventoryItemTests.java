package com.project.estorefront.model.inventory;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryItemTests {
    @Test
    public void testGenerateItemID() {
        InventoryItem item = new InventoryItem();
        String itemID = item.generateItemID();
        System.out.println(itemID);
        assertTrue(itemID != null && itemID.length() == 36);
    }

    @Test
    public void testItemSave() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemDescription("test");
        item.setItemQuantity(1);
        item.setItemPrice(10.0);

        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());
        assertTrue(status == IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS);
    }

    @Test
    public void testItemUpdate() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.update(item.getUserID(), item.getItemCategory(), 10, 10, "Test", "Test", item.getItemID());
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS);
    }

    @Test
    public void testItemUpdateWhereItemDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        String wrongItemId = "ASD";
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.update(item.getUserID(), item.getItemCategory(), 10, 10, "Test", "Test", wrongItemId);

        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.FAILURE);
    }

    @Test
    public void testItemDelete() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.delete(item.getItemID());
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS);
    }

    @Test
    public void testItemDeleteWhereItemDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        String wrongItemId = "ASD";
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.delete(wrongItemId);
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.FAILURE);
    }

    @Test
    public void testGetAllItems() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        ArrayList<IInventoryItem> items = persistence.getAll(item.getUserID());
        assertEquals(1, items.size());
    }

    @Test
    public void testGetAllItemsWhereUserDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();

        ArrayList<IInventoryItem> items = persistence.getAll("123");
        assertNull(items);
    }

    @Test
    public void testGetItemByID() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        IInventoryItem item2 = persistence.getItemByID(item.getItemID());
        assertEquals(item.getItemID(), item2.getItemID());
    }

    @Test
    public void testGetItemByIDWhereItemDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        InventoryItem item = new InventoryItem();
        item.setUserID("123");
        item.setItemName("test");
        item.setItemQuantity(1);
        item.setItemPrice(1.0);
        item.setItemDescription("test");

        persistence.save(item.getItemID(), item.getUserID(), item.getItemCategory(), item.getItemQuantity(), item.getItemPrice(), item.getItemName(), item.getItemDescription());

        String wrongItemId = "ASD";
        IInventoryItem item2 = persistence.getItemByID(wrongItemId);
        assertNull(item2);
    }
}
