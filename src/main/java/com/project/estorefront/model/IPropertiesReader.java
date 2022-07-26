package com.project.estorefront.model;

public interface IPropertiesReader {

    String getSpringDatasourceURL();

    String getSpringDatasourceUsername();

    String getSpringDatasourcePassword();

    String getEmailSenderEmail();

    String getEmailSenderPassword();

    enum PersistenceStatus {
        SUCCESS,
        FAILURE,
        SQL_EXCEPTION,
    }
}
