package com.example.rxusagi.myapplication.model.User_Friend;

import java.sql.Date;

/**
 * Created by RXUsagi on 04/11/2015.
 */
public class People {
    protected String facebookID;
    protected String name;
    protected String surname;
    protected String gender;
    protected int age;
    protected String status;
    protected int score;
    protected int primaryKey;
    protected String url;

    public People(){
        name = "";
        surname = "";
        gender = "";
        status = "";
        score = 0;
        age = 0;
        primaryKey = -1;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }


    public int getPrimaryKey() {
        return primaryKey;
    }

    public int getScore() {
        return score;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSurname() {
        return surname;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void update(){

    }
}
