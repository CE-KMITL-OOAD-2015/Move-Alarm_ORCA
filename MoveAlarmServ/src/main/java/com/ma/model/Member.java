package com.ma.model;


import com.google.gson.Gson;

import java.sql.Date;


/**
 * Created by Admin on 10/16/2015.
 */
public class Member{
    private int pk;
    private String firstname,lastname,gender,email,status;
    private long idFb;
    private int score;
    private Date birthday;
    private int age;


    public Member(){
        this("firstnm dummy","Surnm member","Unknown",0);
    }

    public Member(String firstname,String lastname,String gender,int pk){
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.pk = pk;
    }

    public int getPk() {
        return pk;
    }

    public int getAge() {
        return age;
    }

    public long getIdFb(){return idFb;}

    public Date getBirthday() {
        return birthday;
    }

    public int getScore() {
        return score;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getGender() {
        return gender;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStatus() {
        return status;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdFb(long idFb){
        this.idFb = idFb;
    }

    @Override
    public String toString() {
        return String.format("Name : %s %s \n Age : %d\n gender %s\n",firstname,lastname,age,gender);
    }

    public Member formGson(String json){
        Gson gson = new Gson();
        Member member = formGson(json);
        return member;
    }

}
