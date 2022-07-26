package com.project.estorefront.model.validators;

import com.project.estorefront.model.cart.ICart;
import com.project.estorefront.model.inventory.IInventoryItem;
import com.project.estorefront.model.inventory.IInventoryItemPersistence;

import java.sql.SQLException;

public class CartValidator implements ICartValidator {

    public CartValidator() {
    }

    @Override
    public String validateCart(ICart cart, IInventoryItemPersistence obj) throws SQLException {
        String errors = "";
        //TODO remove concretion
        IInventoryItemPersistence dbItems = obj;

        for (IInventoryItem item : cart.getCartItems()) {
            IInventoryItem dbItem = dbItems.getItemByID(item.getItemID());

            if(dbItem != null)
            {
                if (dbItem.getItemQuantity() < item.getItemQuantity()) {
                    errors += "Error: Only " + dbItem.getItemQuantity() + " pcs. are available in stock for " + item.getItemName();
                    errors += ". Kindly change the quantity to place order!";
                    break;
                }
            }

        }

        return errors;
    }
}
