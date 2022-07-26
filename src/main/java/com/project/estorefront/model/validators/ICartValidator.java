package com.project.estorefront.model.validators;

import com.project.estorefront.model.cart.ICart;
import com.project.estorefront.model.inventory.IInventoryItemPersistence;

import java.sql.SQLException;

public interface ICartValidator {

    String validateCart(ICart cart, IInventoryItemPersistence obj) throws SQLException;
}
