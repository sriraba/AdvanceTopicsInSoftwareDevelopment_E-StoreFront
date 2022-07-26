package com.project.estorefront.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class OTPGeneratorTests {

    @Test
    public void testGenerateOTP() {
        IOTPGenerator otpGenerator = new OTPGenerator();
        String otp = otpGenerator.generateOTP();

        assert (otp.length() == 6);
    }
}
