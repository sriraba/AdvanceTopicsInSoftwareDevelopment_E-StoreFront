package com.project.estorefront.validators;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})class PhoneNumberValidatorTests {

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
