package com.project.estorefront.repository;

import com.project.estorefront.model.User;

public interface IAuthentication {
	User userLogin(String email);
	void userRegistration();
}
