package com.project.estorefront.model.validators;

public interface IValidatorFactory {

    IValidator makeEmailValidator();
    IValidator makePasswordValidator();
    IValidator makeNameValidator();
    IValidator makePhoneNumberValidator();

}
