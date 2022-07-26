package com.project.estorefront.model.inventory;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidator;
import com.project.estorefront.model.database.IDatabase;

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
    public IInventoryItem makeInventoryItem() {
        return new InventoryItem();
    }

    @Override
    public IInventoryItem makeInventoryItemWithItemID(String itemID) {
        return new InventoryItem(itemID);
    }

    @Override
    public IInventoryItem makeInventoryItem(String itemID, String userID, ItemCategory itemCategory, int quantity, double price, String itemName, String itemDescription) {
        return new InventoryItem(itemID, userID, itemCategory, quantity, price, itemName, itemDescription);
    }

    @Override
    public IInventoryItemPersistence makeInventoryItemPersistence(IDatabase database) {
        return new InventoryItemPersistence(database);
    }

    @Override
    public IInventoryItemValidator makeValidator() {
        return new InventoryItemValidator();
    }
}
