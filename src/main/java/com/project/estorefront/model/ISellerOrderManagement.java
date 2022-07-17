package com.project.estorefront.model;

import java.util.ArrayList;
import java.util.Map;

public interface ISellerOrderManagement {

    Map<String, ArrayList<OrderDetails>> getSellerOrders(String userID);
    OrderDetails getOrderAndItemDetails(String orderID);
}
