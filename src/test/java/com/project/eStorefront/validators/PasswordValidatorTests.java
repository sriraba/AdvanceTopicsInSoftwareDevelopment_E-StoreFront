package com.project.eStorefront.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for password validator
 *
 * @author Hrishi Patel
 * @version 1.0
 * @since 16-06-2022
 */

@SpringBootTest
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
