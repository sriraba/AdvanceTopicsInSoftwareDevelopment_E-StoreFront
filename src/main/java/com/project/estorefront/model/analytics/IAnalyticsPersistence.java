package com.project.estorefront.model.analytics;

public interface IAnalyticsPersistence {

    int getTotalOrders();
    int getTotalSales();
    int getTotalReturningBuyers();
    int getNewBuyers();
}
