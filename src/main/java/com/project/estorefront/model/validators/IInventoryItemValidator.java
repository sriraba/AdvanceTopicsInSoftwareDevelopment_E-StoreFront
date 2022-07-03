package com.project.estorefront.model.validators;

import com.project.estorefront.model.IInventoryItem;

public interface IInventoryItemValidator {
    public enum InventoryItemValidationStatus {
        VALID,
        INVALID_INVENTORY_ITEM_ID,
        INVALID_INVENTORY_ITEM_QUANTITY,
        INVALID_INVENTORY_ITEM_PRICE,
        INVALID_INVENTORY_ITEM_NAME,
        INVALID_INVENTORY_ITEM_DESCRIPTION,
        INVALID_USER_ID,
        ALL_INVALID
    }
    public InventoryItemValidationStatus validate(IInventoryItem item);
}
