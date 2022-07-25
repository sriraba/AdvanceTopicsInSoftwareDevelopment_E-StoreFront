package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public class SellerOrderPersistenceMock extends OrderPersistence implements ISellerOrderPersistence {
    private ArrayList<OrderDetails> sellerOrders= new ArrayList<>();

    private ArrayList<OrderDetails> addMockSellerCurrentOrders(){
        ArrayList<OrderDetails> currentOrderList = new ArrayList<>();
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderID("OR12345");
        orderDetail.setOrderStatus("Placed");
        orderDetail.setCouponID("");
        orderDetail.setTotalAmount(60.0F);
        orderDetail.setDeliveryCharges("0.0");
        orderDetail.setDeliveryAddress("133 South Park Street");
        orderDetail.setPincode("B3J2K9");
        orderDetail.setBuyerID("1");
        orderDetail.setSellerID("5");
        currentOrderList.add(orderDetail);
        return currentOrderList;
    }
    private ArrayList<OrderDetails> addMockSellerPreviousOrders(){
        ArrayList<OrderDetails> previousOrderList = new ArrayList<>();
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderID("OR12355");
        orderDetail.setOrderStatus("Delivered");
        orderDetail.setCouponID("");
        orderDetail.setTotalAmount(60.0F);
        orderDetail.setDeliveryCharges("0.0");
        orderDetail.setDeliveryAddress("133 South Park Street");
        orderDetail.setPincode("B3J2K9");
        orderDetail.setBuyerID("2");
        orderDetail.setSellerID("5");
        previousOrderList.add(orderDetail);
        return previousOrderList;
    }

    public void addMockSellerOrders(String sellerID){

        for (OrderDetails orderDetails : addMockSellerCurrentOrders()) {
            if(sellerID.equalsIgnoreCase(orderDetails.getSellerID())){
                sellerOrders.add(orderDetails);
            }
        }

        for (OrderDetails orderDetails : addMockSellerPreviousOrders()) {
            if(sellerID.equalsIgnoreCase(orderDetails.getSellerID())){
                sellerOrders.add(orderDetails);
            }
        }

        if(sellerOrders.size()<0 ){
            sellerOrders=null;
        }
    }
    @Override
    public ArrayList<OrderDetails> loadOrders(String userID) {
        addMockSellerOrders(userID);
        return sellerOrders!=null && sellerOrders.size()>0 ? sellerOrders : null;
    }

    @Override
    public void updateDeliveryPerson(String orderID, String name) {

    }
}
