package com.project.estorefront.model.validators;

public class PasswordValidator implements IPasswordValidator {

    @Override
    public boolean validate(String value) {
        // regex referenced from https://stackoverflow.com/questions/5142103/regex-to-validate-password-strength
        // by user: https://stackoverflow.com/users/20938/alan-moore
        return value.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
    public boolean comparePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}

