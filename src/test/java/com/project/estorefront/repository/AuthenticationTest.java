package com.project.estorefront.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthenticationTest {
	
	
	@Test
	void testValidUserLogin() {
		String validEmail = "sriramya@gmail.com";
		IAuthentication userLoginValidator = new Authentication();		

		//assertTrue(emailValidator.validate(validEmail));
	}
	

}
