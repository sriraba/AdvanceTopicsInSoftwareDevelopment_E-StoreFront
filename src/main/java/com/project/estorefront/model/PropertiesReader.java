package com.project.estorefront.model;

public class PropertiesReader {

    private static PropertiesReader instance;

    private static String springDataSourceURL;
    private static String springDatasourceUsername;
    private static String springDatasourcePassword;
    private static String secretKey;

    private PropertiesReader() {
    }

    public static PropertiesReader instance() {
        if (instance == null) {

            springDatasourceUsername = System.getenv("SPRING_DATASOURCE_USERNAME");
            springDatasourcePassword = System.getenv("SPRING_DATASOURCE_PASSWORD");
            springDataSourceURL = System.getenv("SPRING_DATASOURCE_URL");
            secretKey = System.getenv("SECRET_KEY");

            instance = new PropertiesReader();
        }
        return instance;
    }

    public String getSpringDataSourceURL() {
        return springDataSourceURL;
    }

    public String getSpringDatasourceUsername() {
        return springDatasourceUsername;
    }

    public String getSpringDatasourcePassword() {
        return springDatasourcePassword;
    }

    public String getSecretKey() {
        return secretKey;
    }


}
