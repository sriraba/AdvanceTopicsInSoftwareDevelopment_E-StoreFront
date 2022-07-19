package com.project.estorefront.model;

import java.util.ArrayList;
import java.util.Map;

public interface IBuyerOrderManagement {
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID);
    OrderDetails getOrderAndItemDetails(String orderID);
}
