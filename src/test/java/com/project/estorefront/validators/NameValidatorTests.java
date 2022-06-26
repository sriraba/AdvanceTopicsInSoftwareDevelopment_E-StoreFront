package com.project.estorefront.validators;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for first, last, and seller name validator
 *
 * @author  Hrishi Patel
 * @version 1.0
 * @since   16-06-2022
 */

@SpringBootTest
class NameValidatorTests {

	@Test
	void testValidName() {
		String validName = "hrishi";
		IValidator nameValidator = new NameValidator();

		assertTrue(nameValidator.validate(validName));
	}

	@Test
	void testInvalidNameWithASymbol() {
		String invalidName = "hrishi@";
		IValidator nameValidator = new NameValidator();

		assertFalse(nameValidator.validate(invalidName));
	}

	@Test
	void testInvalidNameWithNumbers() {
		String invalidName = "24hrishi";
		IValidator nameValidator = new NameValidator();

		assertFalse(nameValidator.validate(invalidName));
	}

}
