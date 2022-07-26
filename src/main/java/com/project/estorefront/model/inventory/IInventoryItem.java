package com.project.estorefront.model.inventory;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidationStatus;

import java.sql.SQLException;

public interface IInventoryItem {

    String generateItemID();

    String getItemID();

    String getUserID();

    ItemCategory getItemCategory();

    String getItemName();

    String getItemDescription();

    Double getItemPrice();

    Integer getItemQuantity();

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus save(IInventoryItemPersistence persistence) throws SQLException;

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus delete(IInventoryItemPersistence persistence) throws SQLException;

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus update(IInventoryItemPersistence persistence) throws SQLException;

    InventoryItemValidationStatus validate(IInventoryItemValidator validator);

}
