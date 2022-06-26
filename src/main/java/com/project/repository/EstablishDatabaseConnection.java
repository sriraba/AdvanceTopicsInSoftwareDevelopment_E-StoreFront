package com.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class EstablishDatabaseConnection {

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	@Value("${spring.datasource.url}")
	private String dataSourceUrl = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect = true";

	@Value("${spring.datasource.username}")
	private String dataSourceUsername = "CSCI5308_1_DEVINT_USER";

	@Value("${spring.datasource.password}")
	private String dataSourcePassword = "uB8c3mUaMW";

	public Connection createDataBaseConnection() {
		try {

			Class.forName(driverClassName);
			Connection connection = DriverManager.getConnection(dataSourceUrl, dataSourceUsername,
					dataSourcePassword);			
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
