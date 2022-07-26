package com.project.estorefront.model.authentication;

public interface ICrypto {

    String encryptPassword(String password);

    boolean checkPassword(String password, String hashedPassword);


}
