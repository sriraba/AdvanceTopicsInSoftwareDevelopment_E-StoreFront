package com.project.estorefront.model.cart;

import com.project.estorefront.model.inventory.IInventoryItem;
import com.project.estorefront.model.inventory.InventoryItem;

import java.util.ArrayList;

public class Cart implements ICart {

    private static Cart instance = null;
    private ArrayList<IInventoryItem> items = null;

    public Cart() {
        items = new ArrayList<>();
    }

    ;

    public static Cart instance() {
        if (instance == null) {
            instance = new Cart();
        }

        return instance;
    }

    @Override
    public void addItem(IInventoryItem item) {

        int itemIndex = -1;

        for (IInventoryItem cartItem : items) {
            if (cartItem.getItemID().matches(item.getItemID())) {
                itemIndex = items.indexOf(cartItem);
                break;
            }
        }

        if (itemIndex > -1) {
            int existingQty = items.get(itemIndex).getItemQuantity();
            IInventoryItem newItem = items.get(itemIndex);
            ((InventoryItem) newItem).setItemQuantity(existingQty + item.getItemQuantity());
        } else {
            items.add(item);
        }
    }

    @Override
    public void removeItem(IInventoryItem item) {

        IInventoryItem itemToDelete = null;

        for (IInventoryItem cartItem : items) {
            if (cartItem.getItemID().matches(item.getItemID())) {
                itemToDelete = cartItem;
                break;
            }
        }

        if (itemToDelete != null) {
            items.remove(itemToDelete);
        }

    }

    @Override
    public void updateItem(IInventoryItem item) {
        int itemIndex = items.indexOf(item);
        IInventoryItem newItem = items.get(itemIndex);
        ((InventoryItem) newItem).setItemQuantity(item.getItemQuantity());
    }

    @Override
    public void clearCart() {
        items.clear();
        instance = null;

    }

    @Override
    public int getTotalItems() {
        int totalItems = 0;

        for (IInventoryItem item : items) {
            totalItems += item.getItemQuantity();
        }

        return totalItems;
    }

    @Override
    public double getTotal() {
        double amt = 0;

        for (IInventoryItem item : items) {
            amt += item.getItemPrice() * item.getItemQuantity();
        }

        return amt;
    }

    @Override
    public ArrayList<IInventoryItem> getCartItems() {
        return items;
    }

    @Override
    public IInventoryItem getItemByID(String id) {
        IInventoryItem item = null;

        for (IInventoryItem cartItem : items) {
            if (cartItem.getItemID().matches(id)) {
                item = cartItem;
                break;
            }
        }

        return item;
    }

    @Override
    public void pay() {
    }
}
