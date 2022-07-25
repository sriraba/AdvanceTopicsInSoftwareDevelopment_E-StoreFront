package com.project.estorefront.model;

public interface IMailSender {
    void initMailSender();

    boolean sendMail(String to, String otp);
}
