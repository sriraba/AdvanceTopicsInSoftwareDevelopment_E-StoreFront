package com.project.estorefront.model;

public class CryptoFactory implements ICryptoFactory {

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
