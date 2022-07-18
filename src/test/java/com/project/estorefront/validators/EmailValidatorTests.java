package com.project.estorefront.validators;

import com.project.estorefront.model.validators.EmailValidator;
import com.project.estorefront.model.validators.IValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestPropertySource(properties = {"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true", "SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW"})
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
