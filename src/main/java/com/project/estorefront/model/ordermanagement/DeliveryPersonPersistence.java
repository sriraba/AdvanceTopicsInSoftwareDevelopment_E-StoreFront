package com.project.estorefront.model.ordermanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.database.IDatabase;

public class DeliveryPersonPersistence implements IDeliveryPersonPersistence {

    private IDatabase database;

    public DeliveryPersonPersistence(IDatabase database) {
        this.database = database;
    }

    @Override
    public ArrayList<IDeliveryPerson> getAll(String sellerID) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = database.getConnection();

        ArrayList<IDeliveryPerson> deliveryPersonDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM delivery_charge WHERE seller_id = ?");
            preparedStatement.setString(1, sellerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                IDeliveryPerson deliveryPerson = DeliveryPersonFactory.instance().makeDeliveryPerson();
                deliveryPerson.setPersonName(rs.getString("delivery_person_name"));
                deliveryPersonDetails.add(deliveryPerson);
            }
            return deliveryPersonDetails;
        } catch (SQLException e) {
            e.printStackTrace();
            return deliveryPersonDetails;
        } finally {
            database.closeConnection();
        }
    }
}
