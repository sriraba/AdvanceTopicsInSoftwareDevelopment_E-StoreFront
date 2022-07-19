package com.project.estorefront.model;

import java.util.ArrayList;
import java.util.Map;

public interface IBuyerOrderManagement extends IOrderManagement{
    Map<String, ArrayList<OrderDetails>> getBuyerOrders(String userID);
}
