package com.project.estorefront.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class AuthenticationTest {
	@Test
	public void testNewUserRegistration() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userId = authenticationMock.register(authenticationMock.createUser());
		assertNotNull(userId);
	}

	@Test
	public void testOldUserRegistration() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userId = authenticationMock.register(authenticationMock.getUser("old"));
		assertNull(userId);
	}

	@Test
	public void testEmptyUserValueRegistration() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userId = authenticationMock.register(authenticationMock.getUser(""));
		assertNull(userId);
	}

	@Test
	void testValidUserLogin() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userID = authenticationMock.login("sriramya7666@gmail.com", "ramya@09876");
		assertNotNull(userID);
	}

	@Test
	void testInValidUserLogin() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userID = authenticationMock.login("sriramya766@gmail.com", "ramya@09876");
		assertNull(userID);
	}

}
