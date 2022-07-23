package com.project.estorefront.model.validators;

public class ValidatorFactory implements IValidatorFactory {

    private static ValidatorFactory instance;

    private ValidatorFactory() {
    }

    public static IValidatorFactory instance() {
        if (instance == null) {
            instance = new ValidatorFactory();
        }
        return instance;
    }

    @Override
    public IValidator makeEmailValidator() {
        return new EmailValidator();
    }

    @Override
    public IPasswordValidator makePasswordValidator() {
        return new PasswordValidator();
    }

    @Override
    public IValidator makeNameValidator() {
        return new NameValidator();
    }

    @Override
    public IValidator makePhoneNumberValidator() {
        return new PhoneNumberValidator();
    }
}
