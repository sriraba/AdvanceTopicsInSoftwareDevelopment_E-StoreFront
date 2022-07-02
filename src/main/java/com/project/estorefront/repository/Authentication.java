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

				user = new Buyer(firsrName,lastName,emailAddress,"",phone,password,"","");
			}
			return user;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void userRegistration(User user) {

		try{
			connection = Database.getConnection();
			String persistUserDetails = "insert into user (user_id, first_name, last_name, email, password, contact_num, seller, city, business_name ) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(persistUserDetails);
			preparedStmt.setInt(1,generateNewUserID());
			preparedStmt.setString(2,user.getFirstName());
			preparedStmt.setString(3,user.getLastName());
			preparedStmt.setString(4,user.getEmail());
			preparedStmt.setString(5,user.getPassword());
			preparedStmt.setString(6,user.getPhone());
			preparedStmt.setString(7,user.getIsSeller());
			preparedStmt.setString(8,user.getCity());
			preparedStmt.setString(9,"");
			preparedStmt.execute();

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public int generateNewUserID(){
		try {
			int userId = 0;
			connection = Database.getConnection();
			String userDetailsQuery = "select MAX(user_id) from user";
			PreparedStatement preparedStmt = connection.prepareStatement(userDetailsQuery);
			resultSet = preparedStmt.executeQuery();
			while(resultSet.next()){
				userId = resultSet.getInt(1);
			}
			return userId+1;

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
