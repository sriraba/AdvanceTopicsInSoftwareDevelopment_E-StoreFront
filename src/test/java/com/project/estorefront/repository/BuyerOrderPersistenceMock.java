package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public class BuyerOrderPersistenceMock extends OrderPersistence implements IBuyerOrderPersistence{

    private ArrayList<OrderDetails> buyerOrders = new ArrayList<>();

    private ArrayList<OrderDetails> addMockBuyerCurrentOrders(){
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
    private ArrayList<OrderDetails> addMockBuyerPreviousOrders(){
        ArrayList<OrderDetails> previousOrderList = new ArrayList<>();
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderID("OR12365");
        orderDetail.setOrderStatus("Delivered");
        orderDetail.setCouponID("");
        orderDetail.setTotalAmount(60.0F);
        orderDetail.setDeliveryCharges("0.0");
        orderDetail.setDeliveryAddress("133 South Park Street");
        orderDetail.setPincode("B3J2K9");
        orderDetail.setBuyerID("1");
        orderDetail.setSellerID("6");
        previousOrderList.add(orderDetail);
        return previousOrderList;
    }

    public void addMockBuyerOrders(String buyerID){
        ArrayList<OrderDetails> currentOrders = new ArrayList<>();
        ArrayList<OrderDetails> previousOrders = new ArrayList<>();

        for (OrderDetails orderDetails : addMockBuyerCurrentOrders()) {
            if(buyerID.equalsIgnoreCase(orderDetails.getBuyerID())){
                buyerOrders.add(orderDetails);
            }
        }

        for (OrderDetails orderDetails : addMockBuyerPreviousOrders()) {
            if(buyerID.equalsIgnoreCase(orderDetails.getBuyerID())){
                buyerOrders.add(orderDetails);
            }
        }
        if(buyerOrders.size()<0 ){
            buyerOrders =null;
        }

    }
    @Override
    public ArrayList<OrderDetails> loadOrders(String userID) {
        addMockBuyerOrders(userID);
        return buyerOrders !=null && buyerOrders.size()>0 ? buyerOrders : null;
    }

    @Override
    public void submitReview(String userID, String orderID, String description) {

    }
}
