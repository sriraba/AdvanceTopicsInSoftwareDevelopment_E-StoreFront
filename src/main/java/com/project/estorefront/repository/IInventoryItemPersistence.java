package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IInventoryItemPersistence {

    public boolean save(IInventoryItem item) throws SQLException;
    public boolean delete(IInventoryItem item);
    public boolean update(IInventoryItem item);
    public ArrayList<IInventoryItem> getAll(String userID);

}