package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryItemPersistenceMock implements IInventoryItemPersistence {

    private ArrayList<IInventoryItem> inventoryItems;

    public InventoryItemPersistenceMock() {
        this.inventoryItems = new ArrayList<>();
    }

    @Override
    public boolean save(IInventoryItem item) throws SQLException {
        return inventoryItems.add(item);
    }

    @Override
    public boolean delete(IInventoryItem item) {
        return inventoryItems.remove(item);
    }

    @Override
    public boolean update(IInventoryItem item) {
        InventoryItem current;
        for (IInventoryItem i: inventoryItems) {
            if (i.getItemID().equals(item.getItemID())) {
                current = (InventoryItem) i;
                current.setItemName(item.getItemName());
                current.setItemDescription(item.getItemDescription());
                current.setItemCategory(item.getItemCategory());
                current.setItemQuantity(item.getItemQuantity());
                current.setItemPrice(item.getItemPrice());
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<IInventoryItem> getAll(String userID) {
        return inventoryItems.size() > 0 ? inventoryItems : null;
    }

    @Override
    public IInventoryItem getItemByID(String itemID) {
        for (IInventoryItem i: inventoryItems) {
            if (i.getItemID().equals(itemID)) {
                return i;
            }
        }
        return null;
    }
}
