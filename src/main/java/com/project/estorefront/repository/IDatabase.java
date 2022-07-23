package com.project.estorefront.repository;

import java.sql.Connection;

public interface IDatabase {

    Connection getConnection();

    void closeConnection();
}
