package com.project.estorefront.model.validators;

public class EmailValidator implements IValidator {

    @Override
    public boolean validate(String value) {
        // regex generated using https://regex-generator.olafneumann.org/
        return value.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    }
}

