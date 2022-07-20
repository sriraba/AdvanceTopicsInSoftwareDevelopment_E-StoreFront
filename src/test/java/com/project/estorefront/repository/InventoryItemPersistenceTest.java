package com.project.estorefront.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {
                "SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true",
                "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW" })
public class InventoryItemPersistenceTest {

        @Test
        public void testPersistenceSave() throws SQLException {
                IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);
                IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
                persistence.save(item);
                assertTrue(true);
        }

        @Test
        public void testPersistenceDelete() throws SQLException {
                IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
                IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);
                persistence.save(item);
                assertTrue(persistence.delete(item));
        }

        @Test
        public void testPersistenceDeleteWhereItemDoesNotExists() throws SQLException {
                IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
                IInventoryItem item2 = new InventoryItem("2", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);
                assertFalse(persistence.delete(item2));
        }

        @Test
        public void testPersistenceUpdate() throws SQLException {
                IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
                IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);

                persistence.save(item);
                item.setItemName("ASD");

                assertTrue(persistence.update(item));
        }

        @Test
        public void testPersistenceUpdateWhereItemDoesNotExists() throws SQLException {
                IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
                IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);
                IInventoryItem item2 = new InventoryItem("2", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);

                persistence.save(item);
                item.setItemName("ASD");

                assertFalse(persistence.update(item2));
        }

        @Test
        public void testPersistenceGetAll() throws SQLException {
                IInventoryItemPersistence persistence = new InventoryItemPersistenceMock();
                IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);
                IInventoryItem item2 = new InventoryItem("2", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);
                persistence.save(item);
                persistence.save(item2);

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
                IInventoryItem item = new InventoryItem("1", ItemCategory.valueOf("GROCERY"), "Test", "Desc",
                                11.0, 3);

                persistence.save(item);
                IInventoryItem item2 = persistence.getItemByID(item.getItemID());
                assertNotNull(item2);
                ;
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
