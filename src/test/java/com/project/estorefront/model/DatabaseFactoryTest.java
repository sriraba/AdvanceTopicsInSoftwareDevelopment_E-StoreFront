package com.project.estorefront.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseFactoryTest {

    @Test
    public void testDatabaseFactoryInstance() {
        assertNotNull(DatabaseFactory.instance());
    }

}
