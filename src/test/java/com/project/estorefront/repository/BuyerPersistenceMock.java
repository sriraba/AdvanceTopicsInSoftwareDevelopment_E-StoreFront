package com.project.estorefront.repository;

import java.util.ArrayList;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.User;

public class BuyerPersistenceMock implements IBuyerPersistence {
    ArrayList<User> buyers = new ArrayList<>();

    @Override
    public User getBuyerByID(String buyerID) {
        return null;
    }

    @Override
    public boolean deactivateBuyerAccount(User buyer) {
        for (User u : buyers) {
            if (u.getIsUserEnabled() == buyer.getIsUserEnabled()) {
                buyer.setIsUserEnabled(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateBuyerAccount(User buyer) {
        Buyer currentBuyer;
        for (User u : buyers) {
            if (u.getUserID().equals(buyer.getUserID())) {
                currentBuyer = (Buyer) u;
                currentBuyer.setFirstName(buyer.getFirstName());
                currentBuyer.setLastName(buyer.getLastName());
                currentBuyer.setAddress(buyer.getAddress());
                currentBuyer.setPhone(buyer.getPhone());
                currentBuyer.setEmail(buyer.getEmail());
                return true;
            }
        }
        return false;
    }
}
