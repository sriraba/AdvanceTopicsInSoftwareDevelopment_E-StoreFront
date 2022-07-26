package com.project.estorefront.model.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {

    Connection getConnection() throws SQLException;

    void closeConnection();
}
