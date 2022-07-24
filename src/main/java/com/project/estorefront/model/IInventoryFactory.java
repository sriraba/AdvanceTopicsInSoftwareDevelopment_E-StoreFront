package com.project.estorefront.model;

import com.project.estorefront.repository.IInventoryItemPersistence;

public interface IInventoryFactory {

    IInventoryItem makeInventoryItem();

    IInventoryItemPersistence makeInventoryItemPersistence();

}
