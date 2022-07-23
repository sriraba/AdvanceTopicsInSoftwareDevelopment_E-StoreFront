package com.project.estorefront.model;

import java.util.ArrayList;

public interface ICart {

    void addItem(IInventoryItem item);

    void removeItem(IInventoryItem item);

    void updateItem(IInventoryItem item);

    void clearCart();

    void pay();

    int getTotalItems();

    ArrayList<IInventoryItem> getCartItems();

    public IInventoryItem getItemByID(String id);
}
