package com.project.estorefront.model;

import com.project.estorefront.repository.Authentication;
import com.project.estorefront.repository.IAuthentication;
import com.project.estorefront.repository.IDatabase;

public class AuthenticationFactory implements IAuthenticationFactory {

    private static AuthenticationFactory instance;

    private AuthenticationFactory() {
    }

    public static IAuthenticationFactory instance() {
        if (instance == null) {
            instance = new AuthenticationFactory();
        }
        return instance;
    }

    @Override
    public IAuthentication makeAuthentication(IDatabase database) {

        return new Authentication(database);
    }
}
