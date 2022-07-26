package com.project.estorefront.model.validators;

public interface IPasswordValidator extends IValidator {

    boolean comparePassword(String password, String confirmPassword);

}
