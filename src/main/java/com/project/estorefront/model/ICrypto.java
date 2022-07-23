package com.project.estorefront.model;

public interface ICrypto {

    String encryptPassword(String password);

    boolean checkPassword(String password, String hashedPassword);


}
