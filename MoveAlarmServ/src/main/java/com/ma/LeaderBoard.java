package com.ma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 10/26/2015.
 */
public class LeaderBoard {
    private List<Member> memberList;

    public LeaderBoard(){
        this(new String[0]);
    }
    public LeaderBoard(String id[]){
        memberList = new ArrayList<Member>();
    }

    private void sortByScore(){
        Collections.sort(memberList);
        Collections.reverse(memberList);
    }
}
