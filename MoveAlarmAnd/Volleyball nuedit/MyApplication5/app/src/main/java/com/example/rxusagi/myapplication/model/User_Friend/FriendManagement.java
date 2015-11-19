package com.example.rxusagi.myapplication.model.User_Friend;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by RXUsagi on 04/11/2015.
 */
public class FriendManagement {
    public static ArrayList<Friend> friendlist;
    public static int nextfbId;
    public static ArrayList<String> friendFBID;
    public static FriendManagement friendManagement;
    public static boolean wantmorefriend;
    public FriendManagement(){
        friendManagement = this;
        friendlist = new ArrayList<Friend>();
        friendFBID = new ArrayList<String>();
        nextfbId = -1;
        wantmorefriend = false;
    }

    public static ArrayList<String> getFriendFBID() {
        return friendFBID;
    }

    public static FriendManagement instance(){
        return  friendManagement;
    }
    public void addFriend(String fbId,String name,String surname,String gender,String score,String age,String status,String pic,String pk){
        Friend friend = new Friend();
        friend.setFacebookID(fbId);
        friend.setName(name);
        friend.setSurname(surname);
        friend.setGender(gender);
        friend.setScore(Integer.parseInt(score));
        friend.setAge(Integer.parseInt(age));
        friend.setStatus(status);
        friend.setUrl(pic);
        friend.setPrimaryKey(Integer.parseInt(pk));
        Log.i("Friend ADD", friend.getFacebookID());
        Log.i("Friend ADD", friend.getName());
        Log.i("Friend ADD", friend.getSurname());
        Log.i("Friend ADD", friend.getAge()+"");
        Log.i("Friend ADD", friend.getPrimaryKey()+"");
        Log.i("Friend ADD", friend.getScore()+"");
        Log.i("Friend ADD", friend.getStatus()+"");
        Log.i("Friend ADD", friend.getGender());
        Log.i("Friend ADD", friend.getUrl());
        friendlist.add(friend);
    }

    public void clearFBID(){
        friendFBID = new ArrayList<String>();
    }

    public void clearfriendList(){
        friendlist = new ArrayList<Friend>();
    }

    public Friend getFriend(int index){
        return  friendlist.get(index);
    }
    public int getIndexByPk(int primarKey){
        int index = 0;
        while(friendlist.size() > index){
            if(friendlist.get(index).primaryKey == primarKey){
               return index;
            }
            index++;
        }
        return -1;
    }

    public Friend getFriendByfacebookID(String id){
        int index = 0;
        while(friendlist.size() > index){
            if(friendlist.get(index).getFacebookID().equals(id)){
                return getFriend(index);
            }
        }
        return null;
    }
}
