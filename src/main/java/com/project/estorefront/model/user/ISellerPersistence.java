package com.project.estorefront.model.user;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.inventory.ItemCategory;
import com.project.estorefront.model.user.User;

public interface ISellerPersistence {

    ArrayList<User> getAllSellers() throws SQLException;

    ArrayList<User> getAllSellersByCity(String city) throws SQLException;

    ArrayList<User> getAllSellersByCategory(ItemCategory itemCategory, String city) throws SQLException;

    User getSellerByID(String sellerID) throws SQLException;

    boolean deactivateSellerAccount(User seller) throws SQLException;

    boolean updateSellerAccount(User seller) throws SQLException;

}
