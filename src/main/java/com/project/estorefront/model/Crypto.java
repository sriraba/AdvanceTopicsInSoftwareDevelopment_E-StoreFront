package com.project.estorefront.model;

import org.mindrot.jbcrypt.BCrypt;

public class Crypto implements ICrypto {

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
