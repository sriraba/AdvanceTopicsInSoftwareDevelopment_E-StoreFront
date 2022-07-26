package com.project.estorefront.model.authentication;

import com.project.estorefront.model.database.IDatabase;

public interface IAuthenticationFactory {

    IAuthentication makeAuthentication(IDatabase database);

}
