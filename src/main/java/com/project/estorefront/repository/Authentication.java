package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.User;

public class Authentication implements IAuthentication {

	private Connection connection = null;
	private ResultSet resultSet = null;
	
	@Override
	public User userLogin(String email) {
		
		try {
			User user = null;
			connection = Database.getConnection();
			String userDetailsQuery = "select * from user where email =?";
			PreparedStatement preparedStmt = connection.prepareStatement(userDetailsQuery);
			preparedStmt.setString(1, email);
			resultSet = preparedStmt.executeQuery();
			while(resultSet.next()){
				String firsrName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String emailAddress = resultSet.getString("email");
				String phone = resultSet.getString("contact_num");
				String password = resultSet.getString("password");

				user = new Buyer(firsrName,lastName,emailAddress,"",phone,password);
			}
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);			
		}
		
	}

	@Override
	public void userRegistration() {
		
		
	}
	
}
