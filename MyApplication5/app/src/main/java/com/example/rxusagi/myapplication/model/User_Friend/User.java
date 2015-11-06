package com.example.rxusagi.myapplication.model.User_Friend;

import java.sql.Date;
/**
 * Created by RXUsagi on 04/11/2015.
 */
public class User extends People{
    protected String email;
    protected int age;
    protected String birth;

    private static User user;

    public User(){
        super();
        user = this;
        age = 18;
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

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void update(){

    }
}
