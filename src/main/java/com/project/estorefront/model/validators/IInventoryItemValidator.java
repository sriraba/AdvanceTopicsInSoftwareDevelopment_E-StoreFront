package com.project.estorefront.model.validators;

import com.project.estorefront.model.IInventoryItem;

public interface IInventoryItemValidator {
    InventoryItemValidationStatus validate(IInventoryItem item);
}
