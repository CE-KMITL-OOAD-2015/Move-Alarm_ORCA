package com.example.rxusagi.myapplication.model.User_Friend;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.rxusagi.myapplication.model.transfer.Transfer;

/**
 * Created by RXUsagi on 04/11/2015.
 */

public class UserManagement {
    private static UserManagement userManagement;
    public static User user;
    public static FriendManagement friendManagement;
    public static boolean wantLogout;
    public static boolean isguest;
    public UserManagement(SharedPreferences sh){
        wantLogout = false;
        userManagement = this;
        friendManagement = new FriendManagement();
        isguest = sh.getBoolean("isguest",false);
        Log.i("isguest",""+isguest);
    }

    public static User createUser(){
        user = new User();
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(String firstname,String lastname,String gender,String email,String idFb,String birthday,String age,String pic){
        user.setName(firstname);
        user.setSurname(lastname);
        user.setGender(gender);
        user.setEmail(email);
        user.setFacebookID(idFb);
        user.setBirth(birthday);
        user.setAge(Integer.parseInt(age));
        user.setUrl(pic);
        // - status 0 score
        Transfer transfer = new Transfer();
        transfer.regUser(firstname,lastname,gender,email,user.getStatus(),idFb,""+user.getScore(),birthday,age,pic);
    }

    public static void userlogout(){
        wantLogout = false;
    }

    public static void willLogout(){
        wantLogout = true;
    }
    public static UserManagement instance(){
        return userManagement;
    }

    public void clearFriend(){
        friendManagement = new FriendManagement();
    }
}
