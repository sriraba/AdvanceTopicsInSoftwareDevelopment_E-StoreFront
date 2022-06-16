package com.project.eStorefront.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for phone number validator
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   16-06-2022
 */

@SpringBootTest
class PhoneNumberValidatorTests {

	@Test
	void testValidPhone() {
		String validEmail = "9029895569";
		IValidator phoneNumberValidator = new PhoneNumberValidator();

		assertTrue(phoneNumberValidator.validate(validEmail));
	}

	@Test
	void testInvalidPhoneScenario1() {
		String validEmail = "9029895569a";
		IValidator phoneNumberValidator = new PhoneNumberValidator();

		assertFalse(phoneNumberValidator.validate(validEmail));
	}

	@Test
	void testInvalidPhoneScenario2() {
		String validEmail = "902989556";
		IValidator phoneNumberValidator = new PhoneNumberValidator();

		assertFalse(phoneNumberValidator.validate(validEmail));
	}

}
