package com.project.estorefront.model.authentication;

import com.project.estorefront.model.database.IDatabase;

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
