package com.ma.model;

import java.util.Comparator;

/**
 * Created by Admin on 11/4/2015.
 */
public class ScoreSorter  implements Comparator<Member> {

    private static volatile ScoreSorter sorter;
    public static ScoreSorter getInstance() {
        if (sorter == null) {
            synchronized (ScoreSorter.class) {// must test again -- why? This is called "double-checked locking"
                if (sorter == null) {
                    sorter = new ScoreSorter();
                }
            }
        }
        return sorter;
    }
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
