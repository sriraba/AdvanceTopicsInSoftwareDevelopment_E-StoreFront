package com.project.estorefront.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.project.estorefront.model.PropertiesReader;

public class Database implements IDatabase {

    private Connection connection;

    private final String springDataSourceUrl;
    private final String springDataSourceUsername;
    private final String springDataSourcePassword;

    public Database() {
        springDataSourceUrl = PropertiesReader.instance().getSpringDatasourceURL();
        springDataSourceUsername = PropertiesReader.instance().getSpringDatasourceUsername();
        springDataSourcePassword = PropertiesReader.instance().getSpringDatasourcePassword();
        init();
    }

    private void init() {
        try {
            connection = DriverManager.getConnection(springDataSourceUrl,
                    springDataSourceUsername,
                    springDataSourcePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection.isClosed()) {
            init();
        }
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
