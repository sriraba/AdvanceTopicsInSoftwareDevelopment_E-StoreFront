package com.project.estorefront.model.authentication;

public interface IMailSender {
    void initMailSender();

    boolean sendMail(String to, String otp);
}
