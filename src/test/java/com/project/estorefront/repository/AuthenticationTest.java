package com.project.estorefront.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class AuthenticationTest {
    @Test
    public void testNewUserRegistration() {
        AuthenticationMock authenticationMock = new AuthenticationMock();
        String userId = authenticationMock.register("ASd", "ASd", "email@emai.com", "password", "5566556688", true,
                "Halifax", "ABC", "XYZ", "asd", true);
        assertNotNull(userId);
    }

    @Test
    public void testOldUserRegistration() {
        AuthenticationMock authenticationMock = new AuthenticationMock();
        User user = new Buyer("Sri Ramya", "Basam", "sriramya7666@gmail.com", "133 South Park Street ", "9875432466", "ramya@09876", "halifax");
        authenticationMock.addMockUser(user);

        String userId = authenticationMock.register("ASd", "ASd", "sriramya7666@gmail.com", "password", "5566556688", true,
                "Halifax", "ABC", "XYZ", "asd", true);
        assertNull(userId);
    }

    @Test
    public void testEmptyUserValueRegistration() {
        AuthenticationMock authenticationMock = new AuthenticationMock();
        String userId = authenticationMock.register(null, null, null, null, null, false, null, null, null, null, false);
        assertNull(userId);
    }

    @Test
    void testValidUserLogin() {
        AuthenticationMock authenticationMock = new AuthenticationMock();
        User user = new Buyer("Sri Ramya", "Basam", "sriramya7666@gmail.com", "133 South Park Street ", "9875432466", "ramya@09876", "halifax");
        authenticationMock.addMockUser(user);

        String userID = authenticationMock.login("sriramya7666@gmail.com", "ramya@09876");
        assertNotNull(userID);
    }

    @Test
    void testInValidUserLogin() {
        AuthenticationMock authenticationMock = new AuthenticationMock();
        String userID = authenticationMock.login("srsiramya766@gmail.com", "ramya@09876");
        assertNull(userID);
    }

	@Test
	void testIfUserExistsForValidUser() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		boolean isUser = authenticationMock.checkIfUserExists("sriramya@gmail.com");
		assertEquals(true,isUser);
	}
	@Test
	void testIfUserExistsForInvalidUser() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		boolean isUser = authenticationMock.checkIfUserExists("sriramya76698@gmail.com");
		assertEquals(false,isUser);
	}
	@Test
	void testIfUserIsSellerForBuyer() throws SQLException {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		boolean isSeller = authenticationMock.checkIfUserIsSeller("sriramya@gmail.com");
		assertEquals(false,isSeller);
	}
	@Test
	void testIfUserIsSellerForSeller() throws SQLException {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		authenticationMock.makeUserAsSeller();
		boolean isSeller = authenticationMock.checkIfUserIsSeller("sriramya@gmail.com");
		assertEquals(true,isSeller);
	}
	@Test
	void testResetPasswordForValidUser() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		boolean isPasswordReset = authenticationMock.resetPassword("sriramya@gmail.com", "Ramya@987654");
		assertEquals(true,isPasswordReset);
	}
	@Test
	void testResetPasswordForInvalidUser() {
		AuthenticationMock authenticationMock = new AuthenticationMock();
		boolean isPasswordReset = authenticationMock.resetPassword("sriramya76698@gmail.com","Ramya@87654");
		assertEquals(false,isPasswordReset);
	}

}
