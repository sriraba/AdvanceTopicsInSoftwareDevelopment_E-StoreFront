package com.project.estorefront.model.cart;

import com.project.estorefront.model.validators.CartValidator;
import com.project.estorefront.model.validators.ICartValidator;
import com.project.estorefront.model.ordermanagement.IPlaceOrderPersistence;
import com.project.estorefront.model.ordermanagement.PlaceOrderPersistence;

public class CartFactory implements ICartFactory{

    private static CartFactory instance;

    private CartFactory() {
    }

    public static ICartFactory instance() {
        if (instance == null) {
            instance = new CartFactory();
        }
        return instance;
    }

    @Override
    public IPlaceOrderPersistence makeCartPersistence() {
        return new PlaceOrderPersistence();
    }

    @Override
    public ICart makeCart() {
        return Cart.instance();
    }

    @Override
    public ICartValidator makeCartValidator() {
        return new CartValidator();
    }
}
