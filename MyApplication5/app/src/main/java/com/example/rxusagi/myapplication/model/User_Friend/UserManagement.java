package com.example.rxusagi.myapplication.model.User_Friend;

import com.example.rxusagi.myapplication.model.transfer.Transfer;

/**
 * Created by RXUsagi on 04/11/2015.
 */
public class UserManagement {
    private static UserManagement userManagement;
    public static User user;
    public static FriendManagement friendManagement;
    public UserManagement(){
        userManagement = this;
        user = new User();
        friendManagement = new FriendManagement();
    }

    public User getUser() {
        return user;
    }

    public void setUser(String firstname,String lastname,String gender,String email,String idFb,String birthday,String age){
        user.setName(firstname);
        user.setSurname(lastname);
        user.setGender(gender);
        user.setEmail(email);
        user.setFacebookID(idFb);
        user.setBirth(birthday);
        user.setAge(Integer.parseInt(age));
        // - status 0 score
        Transfer transfer = new Transfer();
        transfer.regUser(firstname,lastname,gender,email,user.getStatus(),idFb,""+user.getScore(),birthday,age);
    }

    public static UserManagement instance(){
        return userManagement;
    }

    public void clearFriend(){
        friendManagement = new FriendManagement();
    }
}
