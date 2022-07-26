package com.project.estorefront.model.authentication;

import com.project.estorefront.model.authentication.IMailSender;
import com.project.estorefront.model.validators.IValidator;
import com.project.estorefront.model.validators.ValidatorFactory;

public class MockMailSender implements IMailSender {

    @Override
    public void initMailSender() {
    }

    public boolean sendMail(String to, String otp) {
        return validateEmail(to);
    }

    private boolean validateEmail(String email) {
        IValidator validator = ValidatorFactory.instance().makeEmailValidator();
        return validator.validate(email);
    }

}
