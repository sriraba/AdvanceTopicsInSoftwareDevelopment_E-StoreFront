package com.project.estorefront.model;

public class UserFactory implements IUserFactory {

    private static UserFactory instance = null;

    private UserFactory() {
    }

    public static IUserFactory instance() {
        if (instance == null) {
            instance = new UserFactory();
        }
        return instance;
    }

    public User getUser(String userType) {

        if (userType.equalsIgnoreCase("buyer")) {
            return new Buyer();
        } else if (userType.equalsIgnoreCase("seller")) {
            return new Seller();
        } else {
            return null;
        }

    }
}

