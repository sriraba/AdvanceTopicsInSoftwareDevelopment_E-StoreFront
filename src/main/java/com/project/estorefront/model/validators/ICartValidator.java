package com.project.estorefront.model.validators;

import com.project.estorefront.model.ICart;
import com.project.estorefront.repository.IInventoryItemPersistence;

import java.sql.SQLException;

public interface ICartValidator {

    String validateCart(ICart cart, IInventoryItemPersistence obj) throws SQLException;
}
