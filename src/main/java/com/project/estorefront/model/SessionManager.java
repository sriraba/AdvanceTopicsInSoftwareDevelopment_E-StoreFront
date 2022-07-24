package com.project.estorefront.model;

public class SessionManager implements ISessionManager {

    private static SessionManager instance;

    private String userID;
    private String city;

    private SessionManager() {
    }

    public static SessionManager instance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    @Override
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCity() {
        return city;
    }
}
