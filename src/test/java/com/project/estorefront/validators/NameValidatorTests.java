package com.project.estorefront.validators;

import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.NameValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})class NameValidatorTests {

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
