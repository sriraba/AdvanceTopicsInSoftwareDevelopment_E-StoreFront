package com.project.estorefront.model.authentication;


public class MailSenderFactory implements IMailSenderFactory {

    private static MailSenderFactory instance;

    private MailSenderFactory() {
    }

    public static IMailSenderFactory instance() {
        if (instance == null) {
            instance = new MailSenderFactory();
        }
        return instance;
    }

    @Override
    public IMailSender makeMailSender() {
        return new ResetPasswordMailSender();
    }

    @Override
    public IOTPGenerator makeOTPGenerator() {
        return new OTPGenerator();
    }

}
