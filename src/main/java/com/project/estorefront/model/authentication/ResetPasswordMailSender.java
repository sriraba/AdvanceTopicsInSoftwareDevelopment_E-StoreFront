package com.project.estorefront.model.authentication;

import com.project.estorefront.model.PropertiesReader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class ResetPasswordMailSender implements IMailSender {

    private JavaMailSender emailSender;

    private static final String SUBJECT = "eStorefront - Reset Password";
    private static final String BODY = "Your OTP is: ";

    public ResetPasswordMailSender() {
        initMailSender();
    }

    @Override
    public void initMailSender() {
        if (emailSender == null) {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername(PropertiesReader.instance().getEmailSenderEmail());
            mailSender.setPassword(PropertiesReader.instance().getEmailSenderPassword());

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            this.emailSender = mailSender;
        }
    }

    // reference: https://www.baeldung.com/spring-email
    public boolean sendMail(String to, String otp) {
        String body = generateBody(otp);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("estorefrontdal@gmail.com");
            message.setTo(to);
            message.setSubject(SUBJECT);
            message.setText(body);
            emailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateBody(String otp) {
        return BODY + " " + otp;
    }
}
