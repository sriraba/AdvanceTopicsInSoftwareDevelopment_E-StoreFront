package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerPersistence;
import com.project.estorefront.repository.ISellerPersistence;

import java.sql.SQLException;

public class Buyer extends User implements IBuyer {


    public Buyer() {
        super();
    }

    public Buyer(String buyerID) {
        super();
        this.userID = buyerID;
    }

    public Buyer(String firstName, String lastName, String email, String address, String contact, String password, String city) {
    }

    @Override
    public void updateProfile() {
        // TODO To be implemented
    }

    @Override
    public void deleteProfile() {
        // TODO To be implemented
    }

    @Override
    public boolean updateBuyerAccount(IBuyerPersistence persistence) throws SQLException {
        return persistence.updateBuyerAccount(this);
    }

    @Override
    public boolean deactivateBuyerAccount(IBuyerPersistence persistence) throws SQLException {
        return persistence.deactivateBuyerAccount(this);
    }

    public User getBuyerByID(IBuyerPersistence persistence, String buyerID) throws SQLException {
        return persistence.getBuyerByID(buyerID);
    }
}
