package com.project.estorefront.repository;

import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
public class InventoryItemPersistenceTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        Database.init();
    }

//    @Test
//    public void saveInventoryItemTest() {
//        InventoryItemPersistence inventoryItemPersistence = new InventoryItemPersistence();
//        InventoryItem inventoryItem = new InventoryItem();
//        inventoryItem.setUserID("1");
//        inventoryItem.setItemCategory(ItemCategory.GROCERY);
//        inventoryItem.setItemName("abc");
//        inventoryItem.setItemDescription("abc");
//        inventoryItem.setItemPrice(8.0);
//        inventoryItem.setItemQuantity(2);
//        assertTrue(inventoryItemPersistence.save(inventoryItem));
//    }

}
