package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;
import com.project.estorefront.model.ordermanagement.IBuyerOrderPersistence;
import com.project.estorefront.model.ordermanagement.OrderDetails;
import com.project.estorefront.model.ordermanagement.OrderPersistence;

import java.util.ArrayList;

public class BuyerOrderPersistenceMock extends OrderPersistence implements IBuyerOrderPersistence {

    private ArrayList<OrderDetails> buyerOrders = new ArrayList<>();
    private ArrayList<OrderDetails> orderReviews = new ArrayList<>();
    public BuyerOrderPersistenceMock() {
        super();
    }

    private void addMockOrderReviews(){
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setOrderID("OR12367");
        orderDetail.setOrderStatus("Delivered");
        orderDetail.setBuyerID("8");
        orderDetail.setDescription("On time Delivery!!");
        orderReviews.add(orderDetail);
    }
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
    public IPropertiesReader.PersistenceStatus submitReview(String userID, String orderID, String description) {
        addMockOrderReviews();
        addMockBuyerOrders(userID);
        for (OrderDetails buyerOrder : buyerOrders) {
            if(buyerOrder.getBuyerID().equalsIgnoreCase(userID) && buyerOrder.getOrderID().equalsIgnoreCase(orderID)){
                buyerOrder.setDescription(description);
                return IPropertiesReader.PersistenceStatus.SUCCESS;
            }
        }
        return IPropertiesReader.PersistenceStatus.FAILURE;
    }
}
