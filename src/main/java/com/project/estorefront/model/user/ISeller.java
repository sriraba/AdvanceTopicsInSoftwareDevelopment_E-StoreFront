package com.project.estorefront.model.user;

import java.sql.SQLException;

public interface ISeller {

    String getBusinessName();

    String getBusinessDescription();

    boolean updateSellerAccount(ISellerPersistence persistence) throws SQLException;

    boolean deactivateSellerAccount(ISellerPersistence persistence) throws SQLException;

    User getSellerByID(ISellerPersistence persistence, String sellerID) throws SQLException;

}
