package com.project.estorefront.repository;

import com.project.estorefront.model.ICart;
import com.project.estorefront.model.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderPersistenceMock implements IPlaceOrderPersistence{

    private ArrayList<ICart> carts = new ArrayList<>();
    private ArrayList<OrderDetails> orders = new ArrayList<>();

    @Override
    public boolean placeOrder(ICart cart, String buyerID, String address, String pincode) throws SQLException {
        OrderDetails order = new OrderDetails();
        order.setOrderID("1");
        order.setSellerID(cart.getCartItems().get(0).getUserID());
        order.setOrderStatus("Placed");
        order.setCouponID("");
        order.setTotalAmount((float) cart.getTotal());
        order.setDeliveryCharges("0");
        order.setDeliveryAddress(address);
        order.setPincode(pincode);
        order.setBuyerID(buyerID);

        orders.add(order);
        carts.add(cart);

        if(orders.contains(order) && carts.contains(cart))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
