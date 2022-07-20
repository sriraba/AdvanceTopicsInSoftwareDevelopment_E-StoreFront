package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.project.estorefront.model.DatabaseFactory;
import com.project.estorefront.model.DeliveryPerson;
import com.project.estorefront.model.IDeliveryPerson;

public class DeliveryPersonPersistence implements IDeliveryPersonPersistence {
    private Connection connection;

    @Override
    public ArrayList<IDeliveryPerson> getAll(String sellerID) {
        PreparedStatement preparedStatement = null;
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        Connection connection = database.getConnection();

        ArrayList<IDeliveryPerson> deliveryPersonDetails = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM delivery_charge WHERE seller_id = ?");
            preparedStatement.setString(1, sellerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                IDeliveryPerson deliveryPerson = new DeliveryPerson();
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
