package com.project.estorefront.model.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.NameValidator;

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
