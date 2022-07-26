package com.project.estorefront.model.cart;

import com.project.estorefront.model.cart.CartFactory;
import com.project.estorefront.model.cart.ICart;
import com.project.estorefront.model.validators.ICartValidator;
import com.project.estorefront.model.ordermanagement.IPlaceOrderPersistence;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartFactoryTests {

    @Test
    public void testCartFactoryInstance() {
        assertNotNull(CartFactory.instance());
    }

    @Test
    public void testMakeCartPersistence()
    {
        IPlaceOrderPersistence obj = CartFactory.instance().makeCartPersistence();
        assertNotNull(obj);
    }

    @Test
    public void testMakeCart()
    {
        ICart cart = CartFactory.instance().makeCart();
        assertNotNull(cart);
    }

    public void testMakeCartValidator()
    {
        ICartValidator validator = CartFactory.instance().makeCartValidator();
        assertNotNull(validator);
    }

}
