package com.ma.score;

import com.ma.member.Member;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Admin on 11/4/2015.
 */
public class ScoreCalculator implements Comparator<Member>{

    private static volatile ScoreCalculator scal;
    private Map<Integer,Integer> scoreTable;
    private ScoreDriver scoreDriver;

    public ScoreCalculator(){
        scoreDriver = new ScoreJDBC();
        scoreTable = scoreDriver.getScoreTable();
    }

    public static ScoreCalculator getInstance() {
        if (scal == null) {
            synchronized (ScoreCalculator.class) {// must test again -- why? This is called "double-checked locking"
                if (scal == null) {
                    scal = new ScoreCalculator();
                }
            }
        }
        return scal;
    }

    public int addScore(int point,int exID){
        //soon score cal table
        return scoreTable.get(exID);
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
