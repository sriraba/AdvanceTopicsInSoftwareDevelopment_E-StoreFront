package com.project.estorefront.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true",
		"SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW" })
public class AuthenticationTest {

	@Test
	void testValidUserLogin() {
		String validEmail = "sriramya@gmail.com";
		IAuthentication userLoginValidator = new Authentication();
		// assertTrue(emailValidator.validate(validEmail));
	}

}
