package com.project.estorefront.model.validators;

import com.project.estorefront.model.ICart;

import java.sql.SQLException;

public interface ICartValidator {

    String validateCart(ICart cart) throws SQLException;
}
