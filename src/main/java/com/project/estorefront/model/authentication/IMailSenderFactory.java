package com.project.estorefront.model.authentication;

public interface IMailSenderFactory {

    IMailSender makeMailSender();

    IOTPGenerator makeOTPGenerator();

}
