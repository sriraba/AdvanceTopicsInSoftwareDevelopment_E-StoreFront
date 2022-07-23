package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInventoryItemPersistence {

    enum InventoryItemPersistenceOperationStatus {
        SUCCESS,
        FAILURE,
        SQL_EXCEPTION,
    }

    InventoryItemPersistenceOperationStatus save(IInventoryItem item) throws SQLException;

    InventoryItemPersistenceOperationStatus delete(IInventoryItem item);

    InventoryItemPersistenceOperationStatus update(IInventoryItem item);

    ArrayList<IInventoryItem> getAll(String userID);

    IInventoryItem getItemByID(String itemID);

}