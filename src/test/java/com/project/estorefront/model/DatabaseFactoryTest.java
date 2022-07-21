package com.project.estorefront.model;

import com.project.estorefront.repository.IDatabase;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseFactoryTest {

    @Test
    public void testDatabaseFactoryInstance() {
        assertNotNull(DatabaseFactory.instance());
    }

}
