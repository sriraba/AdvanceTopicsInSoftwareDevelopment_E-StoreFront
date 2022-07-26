package com.project.estorefront.model.cart;

import com.project.estorefront.model.validators.ICartValidator;
import com.project.estorefront.model.ordermanagement.IPlaceOrderPersistence;

public interface ICartFactory {
    IPlaceOrderPersistence makeCartPersistence();
    ICart makeCart();
    ICartValidator makeCartValidator();
}
