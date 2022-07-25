package com.project.estorefront.model;

import com.project.estorefront.model.validators.IInventoryItemValidator;
import com.project.estorefront.repository.IInventoryItemPersistence;

public interface IInventoryFactory {

    IInventoryItem makeInventoryItem();

    IInventoryItemPersistence makeInventoryItemPersistence();

    IInventoryItemValidator makeValidator();

}
