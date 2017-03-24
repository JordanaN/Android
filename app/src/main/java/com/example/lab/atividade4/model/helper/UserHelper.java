package com.example.lab.atividade4.model.helper;

import com.example.lab.atividade4.model.User;

import java.util.HashMap;

/**
 * Created by Lab on 14/03/2017.
 */
public enum UserHelper {
    INSTANCE;

    HashMap<String, User> users = new HashMap<>();

    public User find(String email){
        return users.get(email);
    }

    public void add(String email, User user){
        users.put(email,user);
    }
}
