package com.project.estorefront.model.validators;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.PasswordValidator;
import com.project.estorefront.model.validators.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.PasswordValidator;

class PasswordValidatorTests {

    @Test
    void testValidPassword() {
        String validPassword = "Aaadsdwewer@234!1";
        IValidator passwordValidator = new PasswordValidator();

        assertTrue(passwordValidator.validate(validPassword));
    }

    @Test
    void testInvalidPasswordWithLessThanEightChars() {
        String invalidPassword = "abs@1";
        IValidator passwordValidator = new PasswordValidator();

        assertFalse(passwordValidator.validate(invalidPassword));
    }

    @Test
    void testInvalidPasswordWithNoNumber() {
        String invalidPassword = "Aaadsdwewer@";
        IValidator passwordValidator = new PasswordValidator();

        assertFalse(passwordValidator.validate(invalidPassword));
    }

    @Test
    void testInvalidPasswordWithNoSpecialCharacter() {
        String invalidPassword = "Aaadsdwewer2";
        IValidator passwordValidator = new PasswordValidator();

        assertFalse(passwordValidator.validate(invalidPassword));
    }
    @Test
    void testInvalidPasswordWithNoAlphabets(){
        String invalidPassword = "1425637@";
        IValidator passwordValidator = new PasswordValidator();
        assertFalse(passwordValidator.validate(invalidPassword));
    }

}
