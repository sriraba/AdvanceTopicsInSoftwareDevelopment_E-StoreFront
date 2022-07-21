package com.project.estorefront.repository;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.IInventoryItem;
import com.project.estorefront.model.InventoryItem;
import com.project.estorefront.model.ItemCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class InventoryItemPersistence implements IInventoryItemPersistence {

    private Connection connection;

    public InventoryItemPersistence() {

    }

    @Override
    public InventoryItemPersistenceOperationStatus save(IInventoryItem item) {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement = null;
        item.setItemID(UUID.randomUUID().toString());

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO seller_inventory (item_id, user_id, category_id, quantity, price, item_name, item_description) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, item.getItemID());
            preparedStatement.setString(2, item.getUserID());
            preparedStatement.setString(3, item.getItemCategory().toString());
            preparedStatement.setInt(4, item.getItemQuantity());
            preparedStatement.setDouble(5, item.getItemPrice());
            preparedStatement.setString(6, item.getItemName());
            preparedStatement.setString(7, item.getItemDescription());

            int changed = preparedStatement.executeUpdate();
            if (changed > 0) {
                return InventoryItemPersistenceOperationStatus.SUCCESS;
            } else {
                return InventoryItemPersistenceOperationStatus.FAILURE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return InventoryItemPersistenceOperationStatus.SQL_EXCEPTION;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public InventoryItemPersistenceOperationStatus delete(IInventoryItem item) {
        PreparedStatement preparedStatement;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM seller_inventory WHERE item_id = ?");
            preparedStatement.setString(1, item.getItemID());
            int status = preparedStatement.executeUpdate();

            if (status > 0) {
                return InventoryItemPersistenceOperationStatus.SUCCESS;
            } else {
                return InventoryItemPersistenceOperationStatus.FAILURE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return InventoryItemPersistenceOperationStatus.SQL_EXCEPTION;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public InventoryItemPersistenceOperationStatus update(IInventoryItem item) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        try {
            preparedStatement = connection.prepareStatement("UPDATE seller_inventory SET user_id = ?, category_id = ?, quantity = ?, price = ?, item_name = ?, item_description = ? WHERE item_id = ?");
            preparedStatement.setString(1, item.getUserID());
            preparedStatement.setString(2, item.getItemCategory().toString());
            preparedStatement.setInt(3, item.getItemQuantity());
            preparedStatement.setDouble(4, item.getItemPrice());
            preparedStatement.setString(5, item.getItemName());
            preparedStatement.setString(6, item.getItemDescription());
            preparedStatement.setString(7, item.getItemID());

            int status = preparedStatement.executeUpdate();
            if (status > 0) {
                return InventoryItemPersistenceOperationStatus.SUCCESS;
            } else {
                return InventoryItemPersistenceOperationStatus.FAILURE;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return InventoryItemPersistenceOperationStatus.SQL_EXCEPTION;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public ArrayList<IInventoryItem> getAll(String userID) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

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
            return null;
        } finally {
            database.closeConnection();
        }
    }

    @Override
    public IInventoryItem getItemByID(String itemID) {
        PreparedStatement preparedStatement;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        IInventoryItem item = new InventoryItem();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM seller_inventory WHERE item_id = ?");
            preparedStatement.setString(1, itemID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                item.setItemID(rs.getString("item_id"));
                item.setUserID(rs.getString("user_id"));
                item.setItemCategory(ItemCategory.valueOf(rs.getString("category_id")));
                item.setItemQuantity(rs.getInt("quantity"));
                item.setItemPrice(rs.getDouble("price"));
                item.setItemName(rs.getString("item_name"));
                item.setItemDescription(rs.getString("item_description"));
            }

            return item;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            database.closeConnection();
        }
    }
}