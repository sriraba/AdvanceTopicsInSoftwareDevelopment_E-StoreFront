package com.project.estorefront.model.analytics;

public interface IAnalyticsPersistence {

    int getTotalOrders(String userID);
    int getTotalSales(String userID);
    int getTotalReturningBuyers(String userID);
}
