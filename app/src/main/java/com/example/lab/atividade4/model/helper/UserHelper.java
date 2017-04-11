package com.example.lab.atividade4.model.helper;


import com.example.lab.atividade4.model.User;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Lab on 14/03/2017.
 */
public enum UserHelper {
    INSTANCE;

    HashMap<String, User> users = new LinkedHashMap<>();

    public User find(String email) {
        return users.get(email);
    }

    public void add(User user) {
        users.put(user.email, user);
    }

    public User getUserByEmail(String email)
    {
        return users.get(email);
    }
}