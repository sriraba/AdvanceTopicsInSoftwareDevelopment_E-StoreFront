package com.project.eStorefront.validators;

public class NameValidator implements IValidator {

    public boolean validate(String value) {
        // regex generated using https://regex-generator.olafneumann.org/
        return value.matches("^[a-zA-Z]+$");
    }
}
