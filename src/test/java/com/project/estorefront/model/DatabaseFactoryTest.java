package com.project.estorefront.model;

import com.project.estorefront.repository.IDatabase;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseFactoryTest {

    @Test
    void testDatabaseFactoryInstance() {
        assertNotNull(DatabaseFactory.instance());
    }

    @Test
    void testMakeDatabase() {
        IDatabase database = DatabaseFactory.instance().makeDatabase();
        assertNotNull(database);
    }

}
