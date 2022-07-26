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

import static org.junit.Assert.*;

public class InventoryItemPersistenceTest {

    @Test
    public void testPersistenceSave() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        persistence.save("1", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");
        assertTrue(true);
    }

    @Test
    public void testPersistenceDelete() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        persistence.save("1", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.delete("1");
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS);
    }

    @Test
    public void testPersistenceDeleteWhereItemDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        IInventoryItem item2 = new InventoryItem("2", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                11.0, 3);
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.delete(item2.getItemID());
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.FAILURE);

    }

    @Test
    public void testPersistenceUpdate() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        persistence.save("1", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");
        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.update("1", ItemCategory.GROCERY, 10, 20, "Test", "Test", "1");
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.SUCCESS);
    }

    @Test
    public void testPersistenceUpdateWhereItemDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        persistence.save("1", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");
        persistence.save("2", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");

        IInventoryItemPersistence.InventoryItemPersistenceOperationStatus status = persistence.update("1", ItemCategory.GROCERY, 10, 20, "Test", "Test", "6");
        assertSame(status, IInventoryItemPersistence.InventoryItemPersistenceOperationStatus.FAILURE);
    }

    @Test
    public void testPersistenceGetAll() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        persistence.save("1", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");
        persistence.save("2", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");

        ArrayList<IInventoryItem> items = persistence.getAll("1");
        assertEquals(2, items.size());

    }

    @Test
    public void testPersistenceGetAllWhereUserDoesNotExists() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        ArrayList<IInventoryItem> items = persistence.getAll("3");
        assertNull(items);
    }

    @Test
    public void testPersistenceGetItemByID() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();

        persistence.save("1", "1", ItemCategory.GROCERY, 3, 11.0, "Test", "Desc");
        IInventoryItem item2 = persistence.getItemByID("1");
        assertNotNull(item2);
    }

    @Test
    public void testPersistenceGetItemByIDWhereItemDoesntExist() throws SQLException {
        IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
        IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                11.0, 3);

        IInventoryItem item2 = persistence.getItemByID(item.getItemID());
        assertNull(item2);
        ;
    }

}
