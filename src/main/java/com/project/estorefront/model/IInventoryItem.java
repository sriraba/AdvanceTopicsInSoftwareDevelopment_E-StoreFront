package com.project.estorefront.model;

import com.project.estorefront.model.validators.IInventoryItemValidator;
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

    void setItemID(String itemID);

    void setUserID(String userID);

    void setItemCategory(ItemCategory itemCategory);

    void setItemName(String itemName);

    void setItemDescription(String itemDescription);

    void setItemPrice(Double itemPrice);

    void setItemQuantity(Integer itemQuantity);

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus save(IInventoryItemPersistence persistence) throws SQLException;

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus delete(IInventoryItemPersistence persistence);

    IInventoryItemPersistence.InventoryItemPersistenceOperationStatus update(IInventoryItemPersistence persistence);

    IInventoryItemValidator.InventoryItemValidationStatus validate(IInventoryItemValidator validator);

}
