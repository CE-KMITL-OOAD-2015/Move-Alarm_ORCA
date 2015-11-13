package com.ma.model;

import java.util.*;

/**
 * Created by Admin on 10/26/2015.
 */
public class LeaderBoard  {
    private List<Member> leaderboard;
    private ScoreSorter sorter;

    public LeaderBoard(){
        this(new ArrayList<Member>());
    }
    public LeaderBoard(List<Member> memberList){
        leaderboard = new ArrayList<Member>(memberList);
        sorter = ScoreSorter.getInstance();
        Collections.sort(leaderboard,sorter);
    }

    public List<Member> getLeaderboard() {
        Collections.reverse(leaderboard);
        return leaderboard;
    }

}
