package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.ItemCategory;
import com.project.estorefront.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISellerPersistence {

    ArrayList<User> getAllSellers() throws SQLException;

    ArrayList<User> getAllSellersByCity(String city);

    ArrayList<User> getAllSellersByCategory(ItemCategory itemCategory, String city);

    User getSellerByID(String sellerID);


    //boolean deactivateSellerAccount(User seller);

    boolean deactivateSellerAccount(User seller);

    boolean updateSellerAccount(User seller);

}


