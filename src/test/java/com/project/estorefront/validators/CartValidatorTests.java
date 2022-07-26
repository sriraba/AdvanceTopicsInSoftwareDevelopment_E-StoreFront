package com.project.estorefront.validators;

import com.project.estorefront.model.CartFactory;
import com.project.estorefront.model.ICart;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.validators.ICartValidator;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.InventoryItemPersistenceMock;
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
