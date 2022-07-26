package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.ordermanagement.ItemDetails;
import com.project.estorefront.model.ordermanagement.IOrderPersistence;
import com.project.estorefront.model.ordermanagement.OrderDetails;

import java.util.ArrayList;

public class OrderPersistenceMock implements IOrderPersistence {

    private OrderDetails orderDetail = new OrderDetails();
    private ArrayList<ItemDetails> itemDetails = new ArrayList<>();

    public void addMockOrderDetails() {
        ItemDetails itemDetail = new ItemDetails();
        orderDetail.setOrderID("OR12365");
        orderDetail.setOrderStatus("Delivered");
        orderDetail.setCouponID("");
        orderDetail.setTotalAmount(60.0F);
        orderDetail.setDeliveryCharges("0.0");
        orderDetail.setDeliveryAddress("133 South Park Street");
        orderDetail.setPincode("B3J2K9");
        orderDetail.setBuyerID("2");
        orderDetail.setSellerID("6");
        ;
        itemDetail.setItemID("ITEM3");
        itemDetail.setQuantity(1);
        itemDetail.setItemPrice(60.0F);
        itemDetails.add(itemDetail);

        orderDetail.setItemDetails(itemDetails);

    }

    @Override
    public OrderDetails loadOrderAndItems(String orderID) {
        addMockOrderDetails();
        if (orderID.isEmpty() || orderID == null || orderDetail == null) {
            return null;
        }
        return orderDetail.getOrderID().equalsIgnoreCase(orderID) ? orderDetail : null;
    }


}
