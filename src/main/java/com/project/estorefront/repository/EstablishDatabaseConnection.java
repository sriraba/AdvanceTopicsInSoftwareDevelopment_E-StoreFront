package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class EstablishDatabaseConnection {

	private static Connection connection;

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	@Value("${spring.datasource.url}")
	private String dataSourceUrl = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect = true";

	@Value("${spring.datasource.username}")
	private String dataSourceUsername = "CSCI5308_1_DEVINT_USER";

	@Value("${spring.datasource.password}")
	private String dataSourcePassword = "uB8c3mUaMW";

	@PostConstruct
	public void init() {
	createDataBaseConnection();
	}

	private void createDataBaseConnection() {
		try {
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(dataSourceUrl, dataSourceUsername,
					dataSourcePassword);			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDataSourceUsername() {
		return dataSourceUsername;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
