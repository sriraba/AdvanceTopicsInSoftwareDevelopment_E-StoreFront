package com.project.eStorefront.validators;

public class PhoneNumberValidator implements IValidator {
    @Override
    public boolean validate(String value) {
        return value.matches("^[0-9]{10}$");
    }
}
