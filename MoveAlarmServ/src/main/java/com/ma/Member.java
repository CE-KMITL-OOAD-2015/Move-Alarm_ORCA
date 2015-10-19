package com.ma;

import java.sql.Date;



/**
 * Created by Admin on 10/16/2015.
 */
public class Member {
    private String firstname,lastname,gender,email,status;
    private Score score;
    private Date birthday;
    private int age;

    public Member(String firstname,String lastname,String gender){
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Score getScore() {
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

    public void setScore(Score score) {
        this.score = score;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Name : %s %s \n Age : %d\n gender %s",firstname,lastname,age,gender);
    }
}
