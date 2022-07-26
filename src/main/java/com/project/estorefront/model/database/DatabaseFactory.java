package com.project.estorefront.model.database;

public class DatabaseFactory implements IDatabaseFactory {

    private static DatabaseFactory instance;

    private DatabaseFactory() {}

    public static DatabaseFactory instance() {
        if (instance == null) {
            instance = new DatabaseFactory();
        }
        return instance;
    }

    @Override
    public IDatabase makeDatabase() {
        return new Database();
    }
}
