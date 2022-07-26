package com.project.estorefront.model.validators;

import com.project.estorefront.model.validators.EmailValidator;
import com.project.estorefront.model.validators.IValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
