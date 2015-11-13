package com.ma;

/**
 * Created by Admin on 11/4/2015.
 */
public class ScoreCalculator {

    private static volatile ScoreCalculator scal;

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
        return point+exID;
    }

}
