package com.project.estorefront.model.authentication;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class MailSenderTests {

    @Test
    public void testSendMailWithValidEmail() {
        IMailSender mailSender = new MockMailSender();
        boolean successful = mailSender.sendMail("hrishipatel99@gmail.com", "666666");

        assert (successful);
    }

    @Test
    public void testSendMailWithInvalidEmail() {
        IMailSender mailSender = new MockMailSender();
        boolean successful = mailSender.sendMail("hrishipatel99@gmail", "666666");

        assertFalse(successful);
    }

}
