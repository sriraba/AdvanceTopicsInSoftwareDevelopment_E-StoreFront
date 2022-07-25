package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.ItemCategory;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInventoryItemPersistence {

    enum InventoryItemPersistenceOperationStatus {
        SUCCESS,
        FAILURE,
        SQL_EXCEPTION,
    }

    InventoryItemPersistenceOperationStatus save(String itemID, String userID, ItemCategory itemCategory, int quantity, double price, String itemName, String itemDescription) throws SQLException;

    InventoryItemPersistenceOperationStatus delete(String itemID);

    InventoryItemPersistenceOperationStatus update(String userID, ItemCategory itemCategory, int quantity, double price, String name, String description, String itemID);

    ArrayList<IInventoryItem> getAll(String userID);

    IInventoryItem getItemByID(String itemID);

}