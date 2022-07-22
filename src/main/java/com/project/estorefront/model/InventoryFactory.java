package com.project.estorefront.model;

import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;

public class InventoryFactory implements IInventoryFactory {

    private static IInventoryFactory instance = null;

    private InventoryFactory() {
    }

    public static IInventoryFactory instance() {
        if (instance == null) {
            instance = new InventoryFactory();
        }
        return instance;
    }

    @Override
    public IInventoryItem makeInventoryItem(String userID, ItemCategory itemCategory, String itemName, String itemDescription, Double itemPrice, Integer itemQuantity) {
        return new InventoryItem(userID, itemCategory, itemName, itemDescription, itemPrice, itemQuantity);
    }

    @Override
    public IInventoryItem makeInventoryItem() {
        return new InventoryItem();
    }

    @Override
    public IInventoryItemPersistence makeInventoryItemPersistence() {
        return new InventoryItemPersistence();
    }
}
