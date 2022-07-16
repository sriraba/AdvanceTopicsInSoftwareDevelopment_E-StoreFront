package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInventoryItemPersistence {

    boolean save(IInventoryItem item) throws SQLException;

    boolean delete(IInventoryItem item);

    boolean update(IInventoryItem item);

    ArrayList<IInventoryItem> getAll(String userID);

    IInventoryItem getItemByID(String itemID);

}