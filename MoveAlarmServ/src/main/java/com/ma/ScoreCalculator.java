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
        /*
        int add = 0;
        switch (exID){
            case 1: add = 2;break;
            case 2: add = 3;break;
            case 3: add = 4;break;
            case 4: add = 5;break;
            case 5: add = 6;break;
            case 6: add = 7;break;
            case 7: add = 8;break;
            case 8: add = 9;break;
            case 9: add = 10;break;
            case 10: add = 11;break;
            case 11: add = 12;break;
            case 12: add = 13;break;
            case 13: add = 14;break;
            case 14: add = 15;break;
            case 15: add = 16;break;
        }*/
        return point+exID;
    }

}
