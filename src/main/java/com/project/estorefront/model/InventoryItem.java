package com.project.estorefront.model;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.repository.IInventoryItemPersistence;

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

    public InventoryItem(String userID, ItemCategory itemCategory, String itemName, String itemDescription, Double itemPrice, Integer itemQuantity) {
        this.itemID = UUID.randomUUID().toString();
        this.userID = userID;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public InventoryItem() {
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

    @Override
    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public Double getItemPrice() {
        return itemPrice;
    }

    @Override
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    @Override
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    @Override
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    @Override
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public boolean save(IInventoryItemPersistence persistence) throws SQLException {
        return persistence.save(this);
    }

    @Override
    public boolean delete(IInventoryItemPersistence persistence) {
        return persistence.delete(this);
    }

    @Override
    public boolean update(IInventoryItemPersistence persistence) {
        return persistence.update(this);
    }

    @Override
    public IInventoryItemValidator.InventoryItemValidationStatus validate(IInventoryItemValidator validator) {
        return validator.validate(this);
    }
}
