package com.example.rxusagi.myapplication.model.User_Friend;

import java.sql.Date;
/**
 * Created by RXUsagi on 04/11/2015.
 */
public class User extends People{
    protected String email;
    protected String birth;

    private static User user = null;

    public User(){
        super();
        user = this;
    }
    public static User instance(){
        return user;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void update(){

    }
}
