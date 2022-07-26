package com.project.estorefront.validators;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PhoneNumberValidatorTests {

    @Test
    void testValidPhone() {
        String validPhone = "9029895569";
        IValidator phoneNumberValidator = new PhoneNumberValidator();

        assertTrue(phoneNumberValidator.validate(validPhone));
    }
    @Test
    void testInvalidPhoneWithALetter() {
        String invalidPhone = "9029895569a";
        IValidator phoneNumberValidator = new PhoneNumberValidator();

        assertFalse(phoneNumberValidator.validate(invalidPhone));
    }

    @Test
    void testInvalidPhoneLessThan10Digits() {
        String invalidPhone = "902989556";
        IValidator phoneNumberValidator = new PhoneNumberValidator();

        assertFalse(phoneNumberValidator.validate(invalidPhone));
    }

    @Test
    void testInvalidPhoneMoreThan10Digits(){
        String invalidPhone = "9029895563";
        IValidator phoneNumberValidator = new PhoneNumberValidator();
        assertFalse(phoneNumberValidator.validate(invalidPhone));
    }
}
