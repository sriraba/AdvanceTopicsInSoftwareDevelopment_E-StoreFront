package com.project.estorefront.model.authentication;

import org.mindrot.jbcrypt.BCrypt;

public interface ICryptoFactory {

    ICrypto makeCrypto();

    class Crypto implements ICrypto {

        @Override
        public String encryptPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }

        @Override
        public boolean checkPassword(String password, String hashedPassword) {
            return BCrypt.checkpw(password, hashedPassword);
        }
    }

    class CryptoFactory implements ICryptoFactory {

        private static ICryptoFactory instance;

        private CryptoFactory() {
        }

        public static ICryptoFactory instance() {
            if (instance == null) {
                instance = new CryptoFactory();
            }
            return instance;
        }


        @Override
        public ICrypto makeCrypto() {
            return new Crypto();
        }

    }
}
