package com.project.estorefront.model;

import com.project.estorefront.repository.IAuthentication;
import com.project.estorefront.repository.IDatabase;

public interface IAuthenticationFactory {

    IAuthentication makeAuthentication(IDatabase database);

}
