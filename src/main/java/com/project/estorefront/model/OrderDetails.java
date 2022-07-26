package com.project.estorefront.model;

import com.project.estorefront.repository.ISellerOrderPersistence;
import com.project.estorefront.repository.SellerOrderPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDetails implements ISellerOrderManagement{

    private String orderID;
    private String sellerID;
    private String orderStatus;
    private Float totalAmount;
    private String couponApplied;
    private Float couponAmount;
    private String deliveryCharges;
    private String deliveryAddress;
    private String pincode;

    private ArrayList<ItemDetails> itemDetails;

    public ArrayList<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCouponApplied() {
        return couponApplied;
    }

    public void setCouponApplied(String couponApplied) {
        this.couponApplied = couponApplied;
    }

    public Float getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Float couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public boolean isEmpty(){
        if(this.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Map<String, ArrayList<OrderDetails>> getSellerOrders(String sellerID) {
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistence();
        ArrayList<OrderDetails> allOrderDetails = orderPersistence.loadOrders(sellerID);
        ArrayList<OrderDetails> currentOrderDetails = new ArrayList<>();
        ArrayList<OrderDetails> previousOrderDetails = new ArrayList<>();
        Map<String, ArrayList<OrderDetails>> sellerOrders = new HashMap<>();
        allOrderDetails.forEach(orderdetail->{
            if(orderdetail.getOrderStatus().equalsIgnoreCase("delivered")){
                previousOrderDetails.add(orderdetail);
            }
            else{
                currentOrderDetails.add(orderdetail);
            }
        });
        sellerOrders.put("current", currentOrderDetails);
        sellerOrders.put("previous", previousOrderDetails);

        return sellerOrders;
    }
    public OrderDetails getOrderAndItemDetails(String orderID){
        ISellerOrderPersistence orderPersistence = new SellerOrderPersistence();
        return orderPersistence.loadOrderAndItems(orderID);
    }
}
