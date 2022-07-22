package com.project.estorefront.model;

import java.util.ArrayList;

public class Cart implements ICart{

    private static Cart instance = null;
    private ArrayList<IInventoryItem> items = null;

    public Cart()
    {
        items = new ArrayList<>();
    };

    public static Cart instance()
    {
        if(instance == null)
        {
            instance = new Cart();
        }
        return instance;
    }

    @Override
    public void addItem(IInventoryItem item) 
    {
        int itemIndex = items.indexOf(item);

        if(itemIndex > -1)
        {
            int existingQty = items.get(itemIndex).getItemQuantity();
            items.get(itemIndex).setItemQuantity(existingQty + item.getItemQuantity());
        }
        else
        {
            items.add(item);
        }
    }

    @Override
    public void removeItem(IInventoryItem item) 
    {
        IInventoryItem itemToDelete = null;
        for(IInventoryItem cartItem : items)
        {
            if(cartItem.getItemID().matches(item.getItemID()))
            {
                itemToDelete = cartItem;
                break;
            }
        }
        if(itemToDelete != null)
        {
            items.remove(itemToDelete);
        }
    }

    @Override
    public void updateItem(IInventoryItem item) 
    {
        int itemIndex = items.indexOf(item);
        items.get(itemIndex).setItemQuantity(item.getItemQuantity());

    }

    @Override
    public void clearCart() 
    {
        items.clear();
        instance = null;
    }

    @Override
    public int getTotalItems()
    {
        int totalItems = 0;
        for(IInventoryItem item : items)
        {
            totalItems += item.getItemQuantity();
        }
        return totalItems;
    }

    @Override
    public ArrayList<IInventoryItem> getCartItems() 
    {
        return items;
    }

    @Override
    public void pay() 
    {
    }
}
