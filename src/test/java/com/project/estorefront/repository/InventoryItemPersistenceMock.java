package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryItemPersistenceMock implements IInventoryItemPersistence {

    private ArrayList<IInventoryItem> inventoryItems;

    public InventoryItemPersistenceMock() {
        this.inventoryItems = new ArrayList<>();
    }

    @Override
    public InventoryItemPersistenceOperationStatus save(String itemID, String userID, ItemCategory itemCategory, int quantity, double price, String itemName, String itemDescription) throws SQLException {
        InventoryItem item = new InventoryItem(itemID, userID, itemCategory, quantity, price, itemName, itemDescription);
        boolean status = inventoryItems.add(item);
        return status ? InventoryItemPersistenceOperationStatus.SUCCESS : InventoryItemPersistenceOperationStatus.FAILURE;
    }

    @Override
    public InventoryItemPersistenceOperationStatus delete(String itemID) {
        for (IInventoryItem i : inventoryItems) {
            if (i.getItemID().equals(itemID)) {
                inventoryItems.remove(i);
                return InventoryItemPersistenceOperationStatus.SUCCESS;
            }
        }
        return InventoryItemPersistenceOperationStatus.FAILURE;
    }

    @Override
    public InventoryItemPersistenceOperationStatus update(String userID, ItemCategory itemCategory, int quantity, double price, String name, String description, String itemID) {
        for (IInventoryItem i : inventoryItems) {
            if (i.getItemID().equals(itemID)) {
                InventoryItem current = (InventoryItem) i;
                current.setItemName(name);
                current.setItemDescription(description);
                current.setItemCategory(itemCategory);
                current.setItemQuantity(quantity);
                current.setItemPrice(price);
                return InventoryItemPersistenceOperationStatus.SUCCESS;
            }
        }
        return InventoryItemPersistenceOperationStatus.FAILURE;
    }

    @Override
    public ArrayList<IInventoryItem> getAll(String userID) {
        return inventoryItems.size() > 0 ? inventoryItems : null;
    }

    @Override
    public IInventoryItem getItemByID(String itemID) {
        for (IInventoryItem i : inventoryItems) {
            if (i.getItemID().equals(itemID)) {
                return i;
            }
        }
        return null;
    }
}
