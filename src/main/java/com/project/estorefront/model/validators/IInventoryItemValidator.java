package com.project.estorefront.model.validators;

import com.project.estorefront.model.inventory.IInventoryItem;

public interface IInventoryItemValidator {
    InventoryItemValidationStatus validate(IInventoryItem item);
}
