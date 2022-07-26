package com.project.estorefront.repository;

import com.project.estorefront.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

public class PlaceOrderPersistenceTests {

    IPlaceOrderPersistence mockDB = new PlaceOrderPersistenceMock();

    @Test
    public void testPlaceOrder() throws SQLException {
        IInventoryItem item = new InventoryItem("1", ItemCategory.GROCERY, "Penne Pasta", "Penne Pasta 500g", 8.99, 1);
        ICart cart = CartFactory.instance().makeCart();
        cart.addItem(item);

        assertTrue(mockDB.placeOrder(cart, "1", "demo", "demo"));
    }

}
