package com.ma.model;

import java.util.*;

/**
 * Created by Admin on 10/26/2015.
 */
public class LeaderBoard  {
    private  List<Member> leaderboard;

    public LeaderBoard(){
        this(new ArrayList<Member>());
    }
    public LeaderBoard(List<Member> memberList){
        leaderboard = new ArrayList<Member>(memberList);
        Comparator<Member> comparator = new ScoreSorter();
        Collections.sort(leaderboard,comparator);
    }

    public List<Member> getLeaderboard() {
        Collections.reverse(leaderboard);
        return leaderboard;
    }



}
