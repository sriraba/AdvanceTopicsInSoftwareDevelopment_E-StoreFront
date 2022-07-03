package com.project.estorefront.repository;

import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class InventoryItemPersistence implements IInventoryItemPersistence {

    private Connection connection;

    public InventoryItemPersistence() {

    }

    @Override
    public boolean save(IInventoryItem item) {
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO seller_inventory (item_id, user_id, category_id, quantity, price, item_name, item_description) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, item.getItemID());
            preparedStatement.setString(2, item.getUserID());
            preparedStatement.setString(3, item.getItemCategory().toString());
            preparedStatement.setInt(4, item.getItemQuantity());
            preparedStatement.setDouble(5, item.getItemPrice());
            preparedStatement.setString(6, item.getItemName());
            preparedStatement.setString(7, item.getItemDescription());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public boolean delete(IInventoryItem item) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM seller_inventory WHERE item_id = ?");
            preparedStatement.setString(1, item.getItemID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(IInventoryItem item) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        try {
            preparedStatement = connection.prepareStatement("UPDATE seller_inventory SET user_id = ?, category_id = ?, quantity = ?, price = ?, item_name = ?, item_description = ? WHERE item_id = ?");
            preparedStatement.setString(1, item.getUserID());
            preparedStatement.setString(2, item.getItemCategory().toString());
            preparedStatement.setInt(3, item.getItemQuantity());
            preparedStatement.setDouble(4, item.getItemPrice());
            preparedStatement.setString(5, item.getItemName());
            preparedStatement.setString(6, item.getItemDescription());
            preparedStatement.setString(7, item.getItemID());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<IInventoryItem> getAll(String userID) {
        PreparedStatement preparedStatement = null;
        Connection connection = Database.getConnection();
        ArrayList<IInventoryItem> inventoryItems = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM seller_inventory WHERE user_id = ?");
            preparedStatement.setString(1, userID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                IInventoryItem item = new InventoryItem();
                item.setItemID(rs.getString("item_id"));
                item.setUserID(rs.getString("user_id"));
                item.setItemCategory(ItemCategory.valueOf(rs.getString("category_id")));
                item.setItemQuantity(rs.getInt("quantity"));
                item.setItemPrice(rs.getDouble("price"));
                item.setItemName(rs.getString("item_name"));
                item.setItemDescription(rs.getString("item_description"));
                inventoryItems.add(item);
            }

            return inventoryItems;
        } catch (SQLException e) {
            e.printStackTrace();
            return inventoryItems;
        }
    }
}