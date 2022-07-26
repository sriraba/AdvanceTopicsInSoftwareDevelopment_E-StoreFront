package com.project.estorefront.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

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
        assert (crypto.checkPassword(password, hashedPassword));
    }

}
