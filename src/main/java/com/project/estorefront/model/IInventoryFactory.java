package com.project.estorefront.model;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.repository.IDatabase;
import com.project.estorefront.repository.IInventoryItemPersistence;

public interface IInventoryFactory {

    IInventoryItem makeInventoryItem();

    IInventoryItem makeInventoryItemWithItemID(String itemID);

    IInventoryItem makeInventoryItem(String itemID, String userID, ItemCategory itemCategory, int quantity, double price, String itemName, String itemDescription);

    IInventoryItemPersistence makeInventoryItemPersistence(IDatabase database);

    IInventoryItemValidator makeValidator();

}
