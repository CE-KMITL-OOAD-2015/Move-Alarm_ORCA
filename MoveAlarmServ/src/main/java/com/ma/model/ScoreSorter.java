package com.ma.model;

import java.util.Comparator;

/**
 * Created by Admin on 11/4/2015.
 */
public class ScoreSorter implements Comparator<Member> {

    @Override
    public int compare(Member o1, Member o2) {
        if(o1.getScore()<o2.getScore())
            return -1;
        else if(o1.getScore()==o2.getScore())
            return 0;
        else
            return 1;
    }
}
