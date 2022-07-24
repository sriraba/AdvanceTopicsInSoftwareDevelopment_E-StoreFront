package com.project.estorefront.model;

import com.project.estorefront.repository.IAuthentication;

public interface IAuthenticationFactory {

    IAuthentication makeAuthentication();

}
