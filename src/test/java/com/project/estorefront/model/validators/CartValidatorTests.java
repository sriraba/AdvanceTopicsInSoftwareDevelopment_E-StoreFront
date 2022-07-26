package com.project.estorefront.model.validators;

import com.project.estorefront.model.cart.CartFactory;
import com.project.estorefront.model.cart.ICart;
import com.project.estorefront.model.inventory.ItemCategory;
import com.project.estorefront.model.inventory.IInventoryItemPersistence;
import com.project.estorefront.model.inventory.InventoryItemPersistenceMock;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CartValidatorTests {

    @Test
    public void testValidateCart() throws SQLException {
        ICart cart = CartFactory.instance().makeCart();
        ICartValidator validator = CartFactory.instance().makeCartValidator();
        IInventoryItemPersistence mockDB = new InventoryItemPersistenceMock();

        mockDB.save("1", "1", ItemCategory.GROCERY, 1, 1, "1", "demo");

        assertEquals("", validator.validateCart(cart, mockDB));
    }
}
