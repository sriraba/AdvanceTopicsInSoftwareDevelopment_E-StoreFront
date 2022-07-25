package com.project.estorefront.model;

import com.project.estorefront.model.validators.ICartValidator;
import com.project.estorefront.repository.IPlaceOrderPersistence;

public interface ICartFactory {
    IPlaceOrderPersistence makeCartPersistence();
    ICart makeCart();
    ICartValidator makeCartValidator();
}
