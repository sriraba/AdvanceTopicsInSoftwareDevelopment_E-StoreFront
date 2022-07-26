package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.cart.ICart;

import java.sql.SQLException;

public interface IPlaceOrderPersistence {
    boolean placeOrder(String userID, ICart cart, String buyerID, String address, String pincode) throws SQLException;
}
