package com.project.estorefront.repository;

import com.project.estorefront.model.ICart;

public interface IPlaceOrderPersistence {
    boolean placeOrder(ICart cart, String buyerID, String address, String pincode);
}
