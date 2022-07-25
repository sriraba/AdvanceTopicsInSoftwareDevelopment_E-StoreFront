package com.project.estorefront.repository;

import com.project.estorefront.model.User;

public interface IAuthentication {
    String login(String email, String password);

    String register(User user);

    boolean resetPassword(String email, String password);

    boolean checkIfUserExists(String email);
}
