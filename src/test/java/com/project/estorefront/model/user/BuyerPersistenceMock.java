package com.project.estorefront.model.user;

import java.util.ArrayList;

import com.project.estorefront.model.user.Buyer;
import com.project.estorefront.model.user.IBuyerPersistence;
import com.project.estorefront.model.user.User;

public class BuyerPersistenceMock implements IBuyerPersistence {
    ArrayList<User> buyers = new ArrayList<>();
    public void addMockBuyer() {
        Buyer buyer = new Buyer();
        buyer.setUserID("1");
        buyer.setEmail("hrishipatel99@gmail.com");
        buyers.add(buyer);
    }
    @Override
    public User getBuyerByID(String buyerID) {
        for (User buyer : buyers) {
            if (buyer.getUserID().equalsIgnoreCase(buyerID)) {
                return buyer;
            }
        }
        return null;
    }


    @Override
    public boolean deactivateBuyerAccount(User buyer) {
        for (User u : buyers) {
            if (u.getUserID() == buyer.getUserID()) {
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
                //currentBuyer.setEmail(buyer.getEmail());
                return true;
            }
        }
        return false;
    }
}
