package com.project.estorefront.model.validators;

public interface IValidatorFactory {

    IValidator makeEmailValidator();
    IPasswordValidator makePasswordValidator();
    IValidator makeNameValidator();
    IValidator makePhoneNumberValidator();

}
