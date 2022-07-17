package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Database {

    private static Connection connection;

    private static Database establishDatabaseConnection = new Database();

    private static final String driverClassName = "com.mysql.jdbc.Driver";

    private static String dataSourceUrl;

    public static String getDriverClassName() {
        return driverClassName;
    }

    private static String dataSourceUsername;

    private static String dataSourcePassword;

    @Autowired
    public Database(
            @Value("${spring.datasource.url}") String dataSourceUrl,
            @Value("${spring.datasource.username}") String dataSourceUsername,
            @Value("${spring.datasource.password}") String dataSourcePassword) {
        this.dataSourceUrl = dataSourceUrl;
        this.dataSourceUsername = dataSourceUsername;
        this.dataSourcePassword = dataSourcePassword;
    }

    public Database() {
    }

    @PostConstruct
    public static void init() {
        createDataBaseConnection();
    }

    private static void createDataBaseConnection() {
        try {
            connection = DriverManager.getConnection(dataSourceUrl, dataSourceUsername,
                    dataSourcePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void destroy() {
        this.closeConnection();
    }
}
