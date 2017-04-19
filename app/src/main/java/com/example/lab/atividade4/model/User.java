package com.example.lab.atividade4.model;

import com.example.lab.atividade4.model.helper.UserHelper;

/**
 * Created by Lab on 14/03/2017.
 */
public class User {
    public long id;
    public String nome;
    public String email;
    public String senha;

    public static User login(String email, String senha)
    {
        User user = UserHelper.INSTANCE.getUserByEmail(email);

        if (user != null)
        {
            if (user.senha.contentEquals(senha))
            {
                return user;
            }
        }

        return null;
    }

    public void save()
    {
        UserHelper.INSTANCE.add(this);
    }
}


