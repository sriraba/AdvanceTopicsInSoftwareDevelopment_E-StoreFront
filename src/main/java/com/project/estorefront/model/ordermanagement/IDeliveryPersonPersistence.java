package com.project.estorefront.model.ordermanagement;

import com.project.estorefront.model.ordermanagement.IDeliveryPerson;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDeliveryPersonPersistence {
    ArrayList<IDeliveryPerson> getAll(String sellerID) throws SQLException;
}
