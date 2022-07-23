package com.project.estorefront.model;

import com.project.estorefront.repository.IOrderPersistence;

public interface IOrderAndItemsFactory {
    IOrderPersistence makeOrderPersistence();
}
