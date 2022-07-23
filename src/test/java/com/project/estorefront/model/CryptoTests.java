package com.project.estorefront.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CryptoTests {

    @Test
    public void testEncryptPassword() {
        ICrypto crypto = CryptoFactory.instance().makeCrypto();

        String password = "password";
        String hashedPassword = crypto.encryptPassword(password);
        assertNotNull(hashedPassword);
    }

    @Test
    public void testCheckPassword() {
        ICrypto crypto = CryptoFactory.instance().makeCrypto();

        String password = "password";
        String hashedPassword = crypto.encryptPassword(password);
        assert(crypto.checkPassword(password, hashedPassword));
    }

}
