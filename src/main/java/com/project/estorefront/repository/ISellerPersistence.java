package com.project.estorefront.repository;

import com.project.estorefront.model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ISellerPersistence {
	
	ArrayList<User> getAllSellers() throws SQLException;
	ArrayList<User> getAllSellersByCity(String city);

}
