package com.project.estorefront.model;

import com.project.estorefront.repository.IInventoryItemPersistence;

public interface IInventoryFactory {

    IInventoryItem makeInventoryItem(String userID, ItemCategory itemCategory, String itemName, String itemDescription, Double itemPrice, Integer itemQuantity);

    IInventoryItem makeInventoryItem();

    IInventoryItemPersistence makeInventoryItemPersistence();

}
