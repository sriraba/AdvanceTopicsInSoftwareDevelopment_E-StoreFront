package com.project.estorefront.model;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.repository.IInventoryItemPersistence;

import java.sql.SQLException;

public interface IInventoryItem {

    public String generateItemID();

    public String getItemID();
    public String getUserID();
    public ItemCategory getItemCategory();
    public String getItemName();
    public String getItemDescription();
    public Double getItemPrice();
    public Integer getItemQuantity();

    public void setItemID(String itemID);
    public void setUserID(String userID);
    public void setItemCategory(ItemCategory itemCategory);
    public void setItemName(String itemName);
    public void setItemDescription(String itemDescription);
    public void setItemPrice(Double itemPrice);
    public void setItemQuantity(Integer itemQuantity);

    public boolean save(IInventoryItemPersistence persistence) throws SQLException;
    public boolean delete(IInventoryItemPersistence persistence);
    public boolean update(IInventoryItemPersistence persistence);

    public IInventoryItemValidator.InventoryItemValidationStatus validate(IInventoryItemValidator validator);

}
