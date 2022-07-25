package com.project.estorefront.model;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidationStatus;
import com.project.estorefront.repository.IInventoryItemPersistence;

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

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus delete(IInventoryItemPersistence persistence);

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus update(IInventoryItemPersistence persistence);

    InventoryItemValidationStatus validate(IInventoryItemValidator validator);

}
