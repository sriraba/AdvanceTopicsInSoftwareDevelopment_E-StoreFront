package com.project.estorefront.repository;

import com.project.estorefront.model.Buyer;
import com.project.estorefront.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationMock implements IAuthentication{
    private static User user = null;
    private static Map<String, User> userMap = new HashMap<>();

    public User createUser(){
        user = new Buyer("Sri Ramya", "Basam", "sriramya7666@gmail.com", "133 South Park Street ", "9875432466", "ramya@09876", "halifax");
        return user;
    }

    public User getUser(String type){
        user = new Buyer("Sri Ramya", "Basam", "sriramya@gmail.com", "133 South Park Street ", "9875432466", "ramya@09876", "halifax");

        String userID = UUID.randomUUID().toString();
        userMap.put(userID, user);
        if("old".equalsIgnoreCase(type)){
            return user;
        }
        else{
            return null;
        }
    }

    public void makeUserAsSeller(){
        user.setIsSeller(true);
    }
    @Override
    public String login(String email, String password) {
        if(userMap.size()>0) {
            for (Map.Entry<String, User> entry : userMap.entrySet()) {
                User userDetail = entry.getValue();
                if (userDetail.getEmail().equalsIgnoreCase(email) && userDetail.getPassword().equalsIgnoreCase(password)) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
    @Override
    public String register(User user) {
        if(userMap.size()>0){
            for (Map.Entry<String,User> entry : userMap.entrySet()){
                User userDetail = entry.getValue();
                if (user == null || userDetail.getEmail().equalsIgnoreCase(user.getEmail()) && userDetail.getPassword().equalsIgnoreCase(user.getPassword())){
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
        if(userMap.size()>0) {
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
        if(userMap.size()>0) {
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
    public boolean checkIfUserIsSeller(String email) throws SQLException {
        if(userMap.size()>0) {
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
