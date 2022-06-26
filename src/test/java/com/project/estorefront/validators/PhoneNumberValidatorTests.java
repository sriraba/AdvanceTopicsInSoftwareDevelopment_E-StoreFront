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

}
