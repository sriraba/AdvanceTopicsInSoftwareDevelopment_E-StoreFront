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
	void testInvalidEmailScenario1() {
		String validEmail = "hrishipatel99@gmail";
		IValidator emailValidator = new EmailValidator();

		assertFalse(emailValidator.validate(validEmail));
	}

	@Test
	void testInvalidEmailScenario2() {
		String validEmail = "hrishipatel99gmail.com";
		IValidator emailValidator = new EmailValidator();

		assertFalse(emailValidator.validate(validEmail));
	}

	@Test
	void testInvalidEmailScenario3() {
		String validEmail = "@a.com";
		IValidator emailValidator = new EmailValidator();

		assertFalse(emailValidator.validate(validEmail));
	}

}
