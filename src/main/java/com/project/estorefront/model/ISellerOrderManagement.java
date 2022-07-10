package com.project.estorefront.model;

import java.util.ArrayList;
import java.util.Map;

public interface ISellerOrderManagement {

    public Map<String, ArrayList<OrderDetails>> getSellerOrders(String userID);
}
