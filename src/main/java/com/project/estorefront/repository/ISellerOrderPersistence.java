package com.project.estorefront.repository;

import com.project.estorefront.model.OrderDetails;

import java.util.ArrayList;

public interface ISellerOrderPersistence {
    public ArrayList<OrderDetails> load(String userID);
}
