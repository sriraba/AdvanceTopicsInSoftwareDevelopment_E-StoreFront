package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerOrderPersistence;
import com.project.estorefront.repository.IOrderPersistence;
import com.project.estorefront.repository.ISellerOrderPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDetailsMock implements ISellerOrderManagement, IBuyerOrderManagement{

    private Map<String, ArrayList<OrderDetails>> buyerOrders = new HashMap<>();
    private OrderDetails orderDetail = new OrderDetails();
    private Map<String, ArrayList<OrderDetails>> sellerOrders = new HashMap<>();
    private ArrayList<ItemDetails> itemDetails = new ArrayList<>();

    public void addMockOrderDetails(){
        ItemDetails itemDetail = new ItemDetails();
        orderDetail.setOrderID("OR12365");
        orderDetail.setOrderStatus("Delivered");
        orderDetail.setCouponID("");
        orderDetail.setTotalAmount(60.0F);
        orderDetail.setDeliveryCharges("0.0");
        orderDetail.setDeliveryAddress("133 South Park Street");
        orderDetail.setPincode("B3J2K9");
        orderDetail.setBuyerID("2");
        orderDetail.setSellerID("6");;
        itemDetail.setItemID("ITEM3");
        itemDetail.setQuantity(1);
        itemDetail.setItemPrice(60.0F);
        itemDetails.add(itemDetail);

        orderDetail.setItemDetails(itemDetails);

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
        orderDetail.setBuyerID("2");
        orderDetail.setSellerID("6");
        previousOrderList.add(orderDetail);
        return previousOrderList;
    }
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

    public void addMockBuyerOrders(String buyerID){
        ArrayList<OrderDetails> currentOrders = new ArrayList<>();
        ArrayList<OrderDetails> previousOrders = new ArrayList<>();

        for (OrderDetails orderDetails : addMockBuyerCurrentOrders()) {
            if(buyerID.equalsIgnoreCase(orderDetails.getBuyerID())){
                currentOrders.add(orderDetails);
            }
        }

        for (OrderDetails orderDetails : addMockBuyerPreviousOrders()) {
            if(buyerID.equalsIgnoreCase(orderDetails.getBuyerID())){
                previousOrders.add(orderDetails);
            }
        }
        if(currentOrders.size()>0 || previousOrders.size()>0){
            buyerOrders.put("current",currentOrders);
            buyerOrders.put("previous",previousOrders);
        }
        else{
            buyerOrders=null;
        }

    }
    public void addMockSellerOrders(String sellerID){
        ArrayList<OrderDetails> currentOrders = new ArrayList<>();
        ArrayList<OrderDetails> previousOrders = new ArrayList<>();

        for (OrderDetails orderDetails : addMockBuyerCurrentOrders()) {
            if(sellerID.equalsIgnoreCase(orderDetails.getSellerID())){
                currentOrders.add(orderDetails);
            }
        }

        for (OrderDetails orderDetails : addMockBuyerPreviousOrders()) {
            if(sellerID.equalsIgnoreCase(orderDetails.getSellerID())){
                previousOrders.add(orderDetails);
            }
        }

        if(currentOrders.size()>0 || previousOrders.size()>0){
            sellerOrders.put("current",currentOrders);
            sellerOrders.put("previous",previousOrders);
        }
        else{
            sellerOrders=null;
        }
    }

    @Override
    public Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID, IBuyerOrderPersistence orderPersistence) {
        addMockBuyerOrders(userID);
        return buyerOrders!=null && buyerOrders.size()>0 ? buyerOrders : null;
    }

    @Override
    public void submitReview(String userID, String orderID, String description) {

    }

    @Override
    public OrderDetails getOrderAndItemDetails(String orderID, IOrderPersistence orderPersistence) {
        addMockOrderDetails();
        if(orderID.isEmpty() || orderID == null || orderDetail==null){
            return null;
        }
        return orderDetail.getOrderID().equalsIgnoreCase(orderID) ? orderDetail : null;
    }

    @Override
    public Map<String, ArrayList<OrderDetails>> getSellerOrders(String userID, ISellerOrderPersistence orderPersistence) {
        addMockSellerOrders(userID);
        return sellerOrders!=null && sellerOrders.size()>0 ? sellerOrders : null;
    }
}
