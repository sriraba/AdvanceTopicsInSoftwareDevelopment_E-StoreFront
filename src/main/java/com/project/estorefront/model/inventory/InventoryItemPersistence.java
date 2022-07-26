package com.project.estorefront.model.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.database.IDatabase;

public class InventoryItemPersistence implements IInventoryItemPersistence {

    private IDatabase database;

    public InventoryItemPersistence(IDatabase database) {
        this.database = database;
    }

    @Override
    public InventoryItemPersistenceOperationStatus save(String itemID, String userID, ItemCategory itemCategory,
            int quantity, double price, String itemName, String itemDescription) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO seller_inventory (item_id, user_id, category_id, quantity, price, item_name, item_description) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, itemID);
            preparedStatement.setString(2, userID);
            preparedStatement.setString(3, itemCategory.toString());
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, price);
            preparedStatement.setString(6, itemName);
            preparedStatement.setString(7, itemDescription);

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
    public InventoryItemPersistenceOperationStatus delete(String itemID) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM seller_inventory WHERE item_id = ?");
            preparedStatement.setString(1, itemID);
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
    public InventoryItemPersistenceOperationStatus update(String userID, ItemCategory itemCategory, int quantity,
            double price, String name, String description, String itemID) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE seller_inventory SET user_id = ?, category_id = ?, quantity = ?, price = ?, item_name = ?, item_description = ? WHERE item_id = ?");
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, itemCategory.toString());
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            preparedStatement.setString(5, name);
            preparedStatement.setString(6, description);
            preparedStatement.setString(7, itemID);

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
    public ArrayList<IInventoryItem> getAll(String userID) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        ArrayList<IInventoryItem> inventoryItems = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM seller_inventory WHERE user_id = ?");
            preparedStatement.setString(1, userID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String itemID = rs.getString("item_id");
                String userID1 = rs.getString("user_id");
                ItemCategory category = ItemCategory.valueOf(rs.getString("category_id"));
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String name = rs.getString("item_name");
                String description = rs.getString("item_description");

                IInventoryItem item = new InventoryItem(itemID, userID1, category, quantity, price, name, description);
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
    public IInventoryItem getItemByID(String itemID) throws SQLException {
        Connection connection = database.getConnection();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM seller_inventory WHERE item_id = ?");
            preparedStatement.setString(1, itemID);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String userID1 = rs.getString("user_id");
                ItemCategory category = ItemCategory.valueOf(rs.getString("category_id"));
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String name = rs.getString("item_name");
                String description = rs.getString("item_description");

                IInventoryItem item = InventoryFactory.instance().makeInventoryItem(itemID, userID1, category, quantity,
                        price, name, description);
                return item;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            database.closeConnection();
        }

        return null;
    }
}