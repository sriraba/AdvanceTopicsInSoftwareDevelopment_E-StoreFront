package com.project.estorefront.model;

import com.project.estorefront.repository.IBuyerPersistence;
import com.project.estorefront.repository.ISellerPersistence;

public class Buyer extends User implements IBuyer{

    public Buyer() {
        super();
    }

    public Buyer(String firstName, String lastName, String email, String address, String contact, String password, String city) {
        super(firstName, lastName, email, address, "", password, city, false, false);
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
    public boolean updateBuyerAccount(IBuyerPersistence persistence) {
        return persistence.updateBuyerAccount(this);
    }

    @Override
    public boolean deactivateBuyerAccount(IBuyerPersistence persistence) {
        return persistence.deactivateBuyerAccount(this);
    }

    public User getBuyerByID(IBuyerPersistence persistence , String buyerID){
        return persistence.getBuyerByID(buyerID);
    }
}
