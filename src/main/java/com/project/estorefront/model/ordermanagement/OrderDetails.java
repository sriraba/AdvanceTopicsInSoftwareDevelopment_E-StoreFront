package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.IPropertiesReader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDetails implements ISellerOrderManagement, IBuyerOrderManagement {
    private String orderID;
    private String sellerID;
    private String orderStatus;
    private String couponID;
    private Float totalAmount;
    private String deliveryCharges;
    private String deliveryAddress;
    private String pincode;
    private String buyerID;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    private ArrayList<ItemDetails> itemDetails;

    public ArrayList<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public String getCouponID() {
        return couponID;
    }

    public void setCouponID(String couponID) {
        this.couponID = couponID;
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

    public boolean isEmpty() {
        if (this.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map<String, ArrayList<OrderDetails>> getSellerOrders(String sellerID,
            ISellerOrderPersistence orderPersistence) throws SQLException {
        ArrayList<OrderDetails> allOrderDetails = orderPersistence.loadOrders(sellerID);
        ArrayList<OrderDetails> currentOrderDetails = new ArrayList<>();
        ArrayList<OrderDetails> previousOrderDetails = new ArrayList<>();
        Map<String, ArrayList<OrderDetails>> sellerOrders = new HashMap<>();
        if (allOrderDetails != null && allOrderDetails.size() > 0) {
            allOrderDetails.forEach(orderdetail -> {
                if (orderdetail.getOrderStatus().equalsIgnoreCase(String.valueOf(OrderStatus.PLACED))) {
                    currentOrderDetails.add(orderdetail);
                } else {
                    previousOrderDetails.add(orderdetail);
                }
            });
            sellerOrders.put("current", currentOrderDetails);
            sellerOrders.put("previous", previousOrderDetails);
            return sellerOrders;
        } else {
            return null;
        }

    }

    @Override
    public Map<String, ArrayList<OrderDetails>> getBuyerOrders(String buyerID, IBuyerOrderPersistence orderPersistence)
            throws SQLException {
        ArrayList<OrderDetails> allOrderDetails = orderPersistence.loadOrders(buyerID);
        ArrayList<OrderDetails> currentOrderDetails = new ArrayList<>();
        ArrayList<OrderDetails> previousOrderDetails = new ArrayList<>();
        Map<String, ArrayList<OrderDetails>> buyerOrders = new HashMap<>();
        if (allOrderDetails != null && allOrderDetails.size() > 0) {
            allOrderDetails.forEach(orderdetail -> {
                if (orderdetail.getOrderStatus().equalsIgnoreCase(String.valueOf(OrderStatus.PLACED)) || orderdetail
                        .getOrderStatus().equalsIgnoreCase(String.valueOf(OrderStatus.DELIVERY_PERSON_ASSIGNED))) {
                    currentOrderDetails.add(orderdetail);
                } else {
                    previousOrderDetails.add(orderdetail);
                }
            });
            buyerOrders.put("current", currentOrderDetails);
            buyerOrders.put("previous", previousOrderDetails);

            return buyerOrders;
        } else {
            return null;
        }

    }

    @Override
    public IPropertiesReader.PersistenceStatus submitReview(String userID, String orderID, String description,
                                                            IBuyerOrderPersistence orderPersistence) throws SQLException {
        return orderPersistence.submitReview(userID, orderID, description);
    }

    public OrderDetails getOrderAndItemDetails(String orderID, IOrderPersistence orderPersistence) throws SQLException {
        return (orderID == null || orderID.isEmpty()) ? null : orderPersistence.loadOrderAndItems(orderID);
    }

    @Override
    public IPropertiesReader.PersistenceStatus updateOrderStatus(String orderID, ISellerOrderPersistence orderPersistence){
        return (orderID == null || orderID.isEmpty()) ? null : orderPersistence.updateOrderStatus(orderID);
    }
}