package com.project.estorefront.model.authentication;

import java.util.Random;

public class OTPGenerator implements IOTPGenerator {

    public String generateOTP() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }
}
