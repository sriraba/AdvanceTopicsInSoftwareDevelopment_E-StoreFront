package com.project.estorefront.model;

public interface IMailSenderFactory {

    IMailSender makeMailSender();

    IOTPGenerator makeOTPGenerator();

}
