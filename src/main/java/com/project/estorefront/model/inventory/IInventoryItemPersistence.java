package com.project.estorefront.model.inventory;

import com.project.estorefront.model.inventory.IInventoryItem;
import com.project.estorefront.model.inventory.ItemCategory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInventoryItemPersistence {

    enum InventoryItemPersistenceOperationStatus {
        SUCCESS,
        FAILURE,
        SQL_EXCEPTION,
    }

    InventoryItemPersistenceOperationStatus save(String itemID, String userID, ItemCategory itemCategory, int quantity, double price, String itemName, String itemDescription) throws SQLException;

    InventoryItemPersistenceOperationStatus delete(String itemID) throws SQLException;

    InventoryItemPersistenceOperationStatus update(String userID, ItemCategory itemCategory, int quantity, double price, String name, String description, String itemID) throws SQLException;

    ArrayList<IInventoryItem> getAll(String userID) throws SQLException;

    IInventoryItem getItemByID(String itemID) throws SQLException;

}