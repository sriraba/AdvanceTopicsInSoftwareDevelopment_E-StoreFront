package com.project.estorefront.model.inventory;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.model.validators.InventoryItemValidationStatus;

import java.sql.SQLException;
import java.util.UUID;

public class InventoryItem implements IInventoryItem {

    private String itemID;
    private String userID;
    private ItemCategory itemCategory;
    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    private Integer itemQuantity;

    public InventoryItem() {
        this.itemID = UUID.randomUUID().toString();
    }

    public InventoryItem(String userID, ItemCategory itemCategory, String itemName, String itemDescription, Double itemPrice, Integer itemQuantity) {
        this.itemID = UUID.randomUUID().toString();
        this.userID = userID;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public InventoryItem(String itemID, String userID, ItemCategory itemCategory, int itemQuantity, double price, String itemName, String itemDescription) {
        this.itemID = itemID;
        this.userID = userID;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = price;
        this.itemQuantity = itemQuantity;
    }

    public InventoryItem(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public String generateItemID() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getItemID() {
        return itemID;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public Double getItemPrice() {
        return itemPrice;
    }

    @Override
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public IInventoryItemPersistence.InventoryItemPersistenceOperationStatus save(IInventoryItemPersistence persistence) throws SQLException {
        return persistence.save(itemID, userID, itemCategory, itemQuantity, itemPrice, itemName, itemDescription);
    }

    @Override
    public IInventoryItemPersistence.InventoryItemPersistenceOperationStatus delete(IInventoryItemPersistence persistence) throws SQLException {
        return persistence.delete(itemID);
    }

    @Override
    public IInventoryItemPersistence.InventoryItemPersistenceOperationStatus update(IInventoryItemPersistence persistence) throws SQLException {
        return persistence.update(userID, itemCategory, itemQuantity, itemPrice, itemName, itemDescription, itemID);
    }

    @Override
    public InventoryItemValidationStatus validate(IInventoryItemValidator validator) {
        return validator.validate(this);
    }
}
