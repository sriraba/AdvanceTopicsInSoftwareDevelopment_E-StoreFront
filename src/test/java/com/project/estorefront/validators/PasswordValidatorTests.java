package com.project.estorefront.validators;

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

}
