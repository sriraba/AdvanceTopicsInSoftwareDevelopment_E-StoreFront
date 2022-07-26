package com.project.estorefront.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class InventoryItemTests {
    @Test
    public void testGenerateItemID() {
        InventoryItem item = new InventoryItem();
        String itemID = item.generateItemID();
        System.out.println(itemID);
        assertTrue( itemID!= null && itemID.length() == 36);
    }

}
