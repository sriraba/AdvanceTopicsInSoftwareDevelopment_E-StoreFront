package com.project.estorefront.model.user;

import java.sql.SQLException;

public interface IBuyer {
    boolean updateBuyerAccount(IBuyerPersistence persistence) throws SQLException;
    boolean deactivateBuyerAccount(IBuyerPersistence persistence) throws SQLException;
    User getBuyerByID(IBuyerPersistence persistence ,String buyerID) throws SQLException;
}
