package com.ma.model;

import java.sql.Date;
import java.util.Calendar;


/**
 * Last modified by Admin on 10/16/2015.
 *  10:33 AM
 */
public class Member{
    private int pk;
    private String firstname,lastname,gender,email,picURL,status;
    private long idFb;
    private int score;
    private Date birthday;
    private int age;


    public Member(){
        this("firstnm dummy","Surnm member","Unknown",0,null);
    }

    public Member(String firstname,String lastname,String gender,int pk,Date birthday){
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.pk = pk;
        if(birthday != null){
            setAge(birthday);
        }
        this.birthday = birthday;
    }

    public int getPk() {
        return pk;
    }

    public int getAge() {
        return age;
    }

    public long getIdFb(){
        return idFb;
    }

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

    public String getPicURL() {
        return picURL;
    }

    public String getStatus() {
        return status;
    }

    public void setAge(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        boolean hbd = (birthday.compareTo(calendar.getTime())<0);
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.setTime(birthday);
        int birthYear = calendar.get(Calendar.YEAR);
        this.age = (hbd)?nowYear-birthYear:nowYear-birthYear+1;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        setAge(birthday);
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

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    @Override
    public String toString() {
        return String.format("Name : %s %s \n Age : %d\n gender %s\n",firstname,lastname,age,gender);
    }
}
