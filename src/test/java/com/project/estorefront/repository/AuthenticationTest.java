package com.project.estorefront.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestPropertySource(properties = {
		"SPRING_DATASOURCE_URL=jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_1_DEVINT?autoreconnect=true",
		"SPRING_DATASOURCE_USERNAME=CSCI5308_1_DEVINT_USER", "SPRING_DATASOURCE_PASSWORD=uB8c3mUaMW" })
public class AuthenticationTest {
	@Test
	public void testNewUserRegistration(){
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userId = authenticationMock.register(authenticationMock.createUser());
		assertNotNull(userId);
	}

	@Test
	public void testOldUserRegistration(){
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userId = authenticationMock.register(authenticationMock.getUser("old"));
		assertNull(userId);
	}
	@Test
	public void testEmptyUserValueRegistration(){
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userId = authenticationMock.register(authenticationMock.getUser(""));
		assertNull(userId);
	}
	@Test
	void testValidUserLogin() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userID = authenticationMock.login("sriramya7666@gmail.com","ramya@09876");
		assertNotNull(userID);
	}
	@Test
	void testInValidUserLogin() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		String userID = authenticationMock.login("sriramya766@gmail.com","ramya@09876");
		assertNull(userID);
	}

}
