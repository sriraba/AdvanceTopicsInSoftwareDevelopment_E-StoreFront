package com.project.eStorefront.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for email validator
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   15-06-2022
 */

@SpringBootTest
class EmailValidatorTests {

	@Test
	void testValidEmail() {
		String validEmail = "hrishipatel99@gmail.com";
		IValidator emailValidator = new EmailValidator();

		assertTrue(emailValidator.validate(validEmail));
	}

	@Test
	void testInvalidEmailWithInvalidDomain() {
		String invalidEmail = "hrishipatel99@gmail";
		IValidator emailValidator = new EmailValidator();

		assertFalse(emailValidator.validate(invalidEmail));
	}

	@Test
	void testInvalidEmailWithoutAt() {
		String invalidEmail = "hrishipatel99gmail.com";
		IValidator emailValidator = new EmailValidator();

		assertFalse(emailValidator.validate(invalidEmail));
	}

	@Test
	void testInvalidEmailWithoutPrefix() {
		String invalidEmail = "@a.com";
		IValidator emailValidator = new EmailValidator();

		assertFalse(emailValidator.validate(invalidEmail));
	}

}
