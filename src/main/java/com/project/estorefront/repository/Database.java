package com.project.estorefront.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.project.estorefront.model.PropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Database implements IDatabase {

    private Connection connection;

    public Database() {
        init();
    }

    private void init() {
        try {
            connection = DriverManager.getConnection(PropertiesReader.instance().getSpringDataSourceURL(),
                    PropertiesReader.instance().getSpringDatasourceUsername(),
                    PropertiesReader.instance().getSpringDatasourcePassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
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
