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

    protected String status;
    protected int score;
    protected int primaryKey;

    public People(){
        name = "name";
        surname = "surname";
        gender = "Jo";
        status = "none";
        score = 0;
        primaryKey = 1;
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
