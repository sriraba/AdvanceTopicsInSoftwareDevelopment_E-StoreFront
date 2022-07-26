package com.project.estorefront.model.validators;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.ICart;
import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.repository.IInventoryItemPersistence;
import com.project.estorefront.repository.InventoryItemPersistence;

import java.sql.SQLException;

public class CartValidator implements ICartValidator {

    public CartValidator() {
    }

    @Override
    public String validateCart(ICart cart) throws SQLException {
        String errors = "";
        //TODO remove concretion
        IInventoryItemPersistence dbItems = new InventoryItemPersistence(DatabaseFactory.instance().makeDatabase());

        for (IInventoryItem item : cart.getCartItems()) {
            IInventoryItem dbItem = dbItems.getItemByID(item.getItemID());

            if (dbItem.getItemQuantity() < item.getItemQuantity()) {
                errors += "Error: Only " + dbItem.getItemQuantity() + " pcs. are available in stock for " + item.getItemName();
                errors += ". Kindly change the quantity to place order!";
                break;
            }

        }

        return errors;
    }
}
