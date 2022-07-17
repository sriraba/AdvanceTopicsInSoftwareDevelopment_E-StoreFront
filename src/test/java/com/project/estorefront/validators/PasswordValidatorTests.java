package com.project.estorefront.validators;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.PasswordValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})class PasswordValidatorTests {

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
