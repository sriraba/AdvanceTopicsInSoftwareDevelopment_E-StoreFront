package com.project.estorefront.repository;

import com.project.estorefront.model.User;

public interface IAuthentication {
	Integer login(String email, String password);
	Integer register(User user);
}
