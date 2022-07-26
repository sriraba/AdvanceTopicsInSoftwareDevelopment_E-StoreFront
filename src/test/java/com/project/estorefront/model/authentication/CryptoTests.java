package com.project.estorefront.model.authentication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.project.estorefront.model.authentication.ICrypto;
import com.project.estorefront.model.authentication.ICryptoFactory;
import org.junit.jupiter.api.Test;

public class CryptoTests {

    @Test
    public void testEncryptPassword() {
        ICrypto crypto = ICryptoFactory.CryptoFactory.instance().makeCrypto();

        String password = "password";
        String hashedPassword = crypto.encryptPassword(password);
        assertNotNull(hashedPassword);
    }

    @Test
    public void testCheckPassword() {
        ICrypto crypto = ICryptoFactory.CryptoFactory.instance().makeCrypto();

        String password = "password";
        String hashedPassword = crypto.encryptPassword(password);
        assert (crypto.checkPassword(password, hashedPassword));
    }

}
