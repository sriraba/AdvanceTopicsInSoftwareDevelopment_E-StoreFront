package com.project.estorefront.model.authentication;

import com.project.estorefront.model.authentication.IOTPGenerator;
import com.project.estorefront.model.authentication.OTPGenerator;
import org.junit.Test;

public class OTPGeneratorTests {

    @Test
    public void testGenerateOTP() {
        IOTPGenerator otpGenerator = new OTPGenerator();
        String otp = otpGenerator.generateOTP();

        assert (otp.length() == 6);
    }
}
