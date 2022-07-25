package com.project.estorefront.model.validators;

public enum InventoryItemValidationStatus {
    VALID("Valid"),
    INVALID_INVENTORY_ITEM_ID("Invalid Inventory Item Id"),
    INVALID_INVENTORY_ITEM_QUANTITY("Invalid Inventory Item Quantity"),
    INVALID_INVENTORY_ITEM_PRICE("Invalid Inventory Item Price"),
    INVALID_INVENTORY_ITEM_NAME("Invalid Inventory Item Name"),
    INVALID_INVENTORY_ITEM_DESCRIPTION("Invalid Inventory Item Description"),
    INVALID_USER_ID("Invalid User ID"),
    ALL_INVALID("All fields are invalid");

    public String label;

    InventoryItemValidationStatus(String label) {
        this.label = label;
    }
}