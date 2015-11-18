package com.example.rxusagi.myapplication.model.User_Friend;

import java.util.ArrayList;

/**
 * Created by RXUsagi on 04/11/2015.
 */
public class FriendManagement {
    private ArrayList<Friend> friendlist;
    public FriendManagement(){
        friendlist = new ArrayList<Friend>();
    }
    public void addFriend(String fbId,String name,String surname,String gender){
        Friend friend = new Friend();
        friend.setFacebookID(fbId);
        friend.setName(name);
        friend.setSurname(surname);
        friend.setGender(gender);
        friendlist.add(friend);
    }

    public Friend getFriend(int index){
        return  friendlist.get(index);
    }
    public void getLeaderboard(){

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

    public void updateallfriendScore_Status(){

    }
}
