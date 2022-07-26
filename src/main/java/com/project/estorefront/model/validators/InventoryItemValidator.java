package com.project.estorefront.model.validators;

import com.project.estorefront.model.inventory.IInventoryItem;

public class InventoryItemValidator implements IInventoryItemValidator {

    @Override
    public InventoryItemValidationStatus validate(IInventoryItem item) {

        boolean validItemID = hasValidItemID(item);
        boolean validItemName = hasValidItemName(item);
        boolean validItemDescription = hasValidItemDescription(item);
        boolean validItemPrice = hasValidItemPrice(item);
        boolean validItemQuantity = hasValidItemQuantity(item);
        boolean validUserID = hasValidUserID(item);

        if (validItemID && validItemName && validItemDescription && validItemPrice && validItemQuantity && validUserID) {
            return InventoryItemValidationStatus.VALID;
        } else if (validUserID == false && validItemID == false && validItemName == false && validItemDescription == false && validItemPrice == false && validItemQuantity == false) {
            return InventoryItemValidationStatus.ALL_INVALID;
        } else {
            if (validItemID == false) {
                return InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_ID;
            } else if (validItemName == false) {
                return InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_NAME;
            } else if (validItemDescription == false) {
                return InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_DESCRIPTION;
            } else if (validItemPrice == false) {
                return InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_PRICE;
            } else if (validItemQuantity == false) {
                return InventoryItemValidationStatus.INVALID_INVENTORY_ITEM_QUANTITY;
            } else {
                return InventoryItemValidationStatus.INVALID_USER_ID;
            }
        }
    }

    private boolean hasValidItemID(IInventoryItem item) {
        if (item.getItemID() == null || item.getItemID().isEmpty()) {
            return false;
        } else {
            return item.getItemID().length() == 36;
        }
    }

    private boolean hasValidItemName(IInventoryItem item) {
        if (item.getItemName() == null || item.getItemName().isEmpty()) {
            return false;
        } else {
            return item.getItemName().length() > 0;
        }
    }

    private boolean hasValidItemDescription(IInventoryItem item) {
        if (item.getItemDescription() == null || item.getItemDescription().isEmpty()) {
            return false;
        } else {
            return item.getItemDescription().length() > 0;
        }
    }

    private boolean hasValidItemPrice(IInventoryItem item) {
        if (item.getItemPrice() == null) {
            return false;
        } else {
            return item.getItemPrice() > 0;
        }
    }

    private boolean hasValidItemQuantity(IInventoryItem item) {
        if (item.getItemQuantity() == null) {
            return false;
        } else {
            return item.getItemQuantity() > 0;
        }
    }

    private boolean hasValidUserID(IInventoryItem item) {
        if (item.getUserID() == null || item.getUserID().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
