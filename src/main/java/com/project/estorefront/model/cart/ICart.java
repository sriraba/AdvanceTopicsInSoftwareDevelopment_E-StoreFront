package com.project.estorefront.model.cart;

import com.project.estorefront.model.inventory.IInventoryItem;

import java.util.ArrayList;

public interface ICart {

    void addItem(IInventoryItem item);

    void removeItem(IInventoryItem item);

    void updateItem(IInventoryItem item);

    void clearCart();

    void pay();

    int getTotalItems();

    double getTotal();

    ArrayList<IInventoryItem> getCartItems();

    IInventoryItem getItemByID(String id);
}
