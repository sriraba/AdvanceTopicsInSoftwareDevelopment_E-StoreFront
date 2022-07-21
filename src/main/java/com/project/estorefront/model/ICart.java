package com.project.estorefront.model;

public interface ICart {

    void addItem(IInventoryItem item);

    void removeItem(IInventoryItem item);

    void updateItem(IInventoryItem item);

    void clearCart();

    void pay();

    int getTotalItems();
}
