package com.project.estorefront.model.authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.project.estorefront.model.user.User;
import com.project.estorefront.model.authentication.IAuthentication;

public class AuthenticationMock implements IAuthentication {
    private User user = null;
    private Map<String, User> userMap = new HashMap<>();

    public void addMockUser(User user) {
        userMap.put("ASD", user);
    }

    public void addMockSeller(User user) {
        user.setIsSeller(true);
        userMap.put("ASD", user);
    }

    @Override
    public User login(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        if (userMap.size() > 0) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User userDetail = entry.getValue();
                if (userDetail.getEmail().equalsIgnoreCase(email)
                        && userDetail.getPassword().equalsIgnoreCase(password)) {
                    return userDetail;
                }
            }
        }
        return null;
    }

    @Override
    public String register(String firstName, String lastName, String email, String password, String phone,
            boolean isSeller, String city, String businessName, String address, String businessDescription,
            boolean isUserEnabled) {
        if (email == null || password == null) {
            return null;
        }
        if (userMap.size() > 0) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User userDetail = entry.getValue();
                if (userDetail.getEmail().equalsIgnoreCase(email)
                        || userDetail.getPassword().equalsIgnoreCase(password)) {
                    return null;
                }
            }
        }
        String userID = UUID.randomUUID().toString();
        userMap.put(userID, user);
        return userID;
    }

    @Override
    public boolean resetPassword(String email, String password) {
        if (userMap.size() > 0) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User userDetail = entry.getValue();
                if (userDetail.getEmail().equalsIgnoreCase(email)) {
                    userDetail.setPassword(password);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkIfUserExists(String email) {
        if (userMap.size() > 0) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User userDetail = entry.getValue();
                if (userDetail.getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkIfUserIsSeller(String email) {
        if (userMap.size() > 0) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User userDetail = entry.getValue();
                if (userDetail.getEmail().equalsIgnoreCase(email) && userDetail.getIsSeller()) {
                    return true;
                }
            }
        }
        return false;
    }
}
