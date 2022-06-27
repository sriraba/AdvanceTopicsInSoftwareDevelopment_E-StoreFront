package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.PostConstruct;

public class EstablishDatabaseConnection implements CommandLineRunner {

	private static Connection connection;
	
	static EstablishDatabaseConnection establishDatabaseConnection = new EstablishDatabaseConnection();

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	@Value("${spring.datasource.url}")
	private String dataSourceUrl = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect = true";

	@Value("${spring.datasource.username}")
	private String dataSourceUsername = "CSCI5308_1_DEVINT_USER";

	@Value("${spring.datasource.password}")
	private String dataSourcePassword = "uB8c3mUaMW";
	
	
	private EstablishDatabaseConnection() {
		
	}
	
	public static EstablishDatabaseConnection instance() {
		 return establishDatabaseConnection;
	}
	
	@PostConstruct
	public void init() {
	createDataBaseConnection();
	}

	private void createDataBaseConnection() {
		try {			
			Class.forName(getDriverClassName());
			connection = DriverManager.getConnection(getDataSourceUrl(), getDataSourceUsername(),
					getDataSourcePassword());			
		} catch (ClassNotFoundException | SQLException e) {			
			throw new RuntimeException(e);
		}
	}

	public String getDataSourceUsername() {
		return dataSourceUsername;
	}

	public String getDriverClassName() {
		return driverClassName;
	}
	
	public String getDataSourceUrl() {
		return dataSourceUrl;
	}	

	public String getDataSourcePassword() {
		return dataSourcePassword;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run(String... args) throws Exception {			
		 EstablishDatabaseConnection db = EstablishDatabaseConnection.instance(); 

		
	}

}
