package com.project.eStorefront.validators;

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
		String validEmail = "hrishi";
		IValidator nameValidator = new NameValidator();

		assertTrue(nameValidator.validate(validEmail));
	}

	@Test
	void testInvalidNameScenario1() {
		String validEmail = "hrishi@";
		IValidator nameValidator = new NameValidator();

		assertFalse(nameValidator.validate(validEmail));
	}

	@Test
	void testInvalidNameScenario2() {
		String validEmail = "24hrishi";
		IValidator nameValidator = new NameValidator();

		assertFalse(nameValidator.validate(validEmail));
	}

}
