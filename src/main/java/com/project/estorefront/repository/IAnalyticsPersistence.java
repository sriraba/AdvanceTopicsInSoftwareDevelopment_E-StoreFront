package com.project.estorefront.repository;

public interface IAnalyticsPersistence {

    int getTotalOrders();
    int getTotalSales();
    int getTotalReturningBuyers();
    int getNewBuyers();
}
