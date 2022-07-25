package com.project.estorefront.model;

public class PropertiesReader implements IPropertiesReader {

    private static PropertiesReader instance;

    private static String springDataSourceURL;
    private static String springDatasourceUsername;
    private static String springDatasourcePassword;

    private static String emailSenderEmail;
    private static String emailSenderPassword;

    private PropertiesReader() {
    }

    public static PropertiesReader instance() {
        if (instance == null) {

            springDatasourceUsername = System.getenv("SPRING_DATASOURCE_USERNAME");
            springDatasourcePassword = System.getenv("SPRING_DATASOURCE_PASSWORD");
            springDataSourceURL = System.getenv("SPRING_DATASOURCE_URL");

            emailSenderEmail = System.getenv("EMAIL_SENDER_EMAIL");
            emailSenderPassword = System.getenv("EMAIL_SENDER_PASSWORD");

            instance = new PropertiesReader();
        }
        return instance;
    }

    public String getSpringDatasourceURL() {
        return springDataSourceURL;
    }

    public String getSpringDatasourceUsername() {
        return springDatasourceUsername;
    }

    public String getSpringDatasourcePassword() {
        return springDatasourcePassword;
    }

    public String getEmailSenderEmail() {
        return emailSenderEmail;
    }

    public String getEmailSenderPassword() {
        return emailSenderPassword;
    }

}
