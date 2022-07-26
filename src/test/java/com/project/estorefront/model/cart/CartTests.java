package com.project.estorefront.model.cart;

import com.project.estorefront.model.cart.Cart;
import com.project.estorefront.model.cart.ICart;
import com.project.estorefront.model.inventory.InventoryItem;
import com.project.estorefront.model.inventory.ItemCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests {

    private ICart cart = null;
    private InventoryItem item = null;

    private void init()
    {
        cart = Cart.instance();
        cart.clearCart();
        item = new InventoryItem("1", ItemCategory.GROCERY, "Penne Pasta", "Penne Pasta 500g", 8.99, 1);
    }

    @Test
    public void testCartInstance()
    {
        init();
        assertNotNull(cart);
    }

    @Test
    public void testAddItem()
    {
        init();
        cart.addItem(item);
        assertEquals(1, cart.getTotalItems());
    }

    @Test
    public void testUpdateItem()
    {
        init();
        cart.addItem(item);
        item.setItemPrice(11.99);
        cart.updateItem(item);
        assertEquals(11.99, cart.getCartItems().get(0).getItemPrice());
    }

    @Test
    public void testDeleteItem()
    {
        init();
        cart.addItem(item);
        cart.removeItem(item);
        assertEquals(0, cart.getTotalItems());
    }

    @Test
    public void testClearCart()
    {
        init();
        cart.addItem(item);
        assertEquals(cart.getTotalItems(), 1);

        cart.clearCart();
        assertEquals(0, cart.getTotalItems());
    }

    @Test
    public void testGetTotal()
    {
        init();
        double itemPrice = item.getItemPrice();
        int qty = 10;
        item.setItemQuantity(qty);
        cart.addItem(item);
        assertEquals(itemPrice * qty, cart.getTotal());
    }

    @Test
    public void testGetItemById()
    {
        init();
        cart.addItem(item);
        assertEquals(item, cart.getItemByID(item.getItemID()));
    }

}
