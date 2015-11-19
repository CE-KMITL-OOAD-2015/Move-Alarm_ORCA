package com.example.rxusagi.myapplication.model;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class AlarmState {
    private boolean activate;
    private int startofficetimehr;
    private int stopofficetimehr;
    private int startofficetimemn;
    private int stopofficetimemn;
    private int periodHr;
    private int periodMn;
    private boolean exstyle;
    private boolean[] workingday;
    private SharedPreferences sharedPreferences;
    public AlarmState(SharedPreferences sh){
        Log.i("TAG2", "StateCre");
        workingday = new boolean[7];
        sharedPreferences = sh;
        reload();
    }

    public void reload(){
        activate = sharedPreferences.getBoolean("ACTIVATE",false);
        startofficetimehr = sharedPreferences.getInt("STARTH", 8);
        startofficetimemn = sharedPreferences.getInt("STARTM", 0);
        stopofficetimehr = sharedPreferences.getInt("STOPH", 16);
        stopofficetimemn = sharedPreferences.getInt("STOPM",0);
        periodHr = sharedPreferences.getInt("PERIODH", 1);
        periodMn = sharedPreferences.getInt("PERIODM",0);
        exstyle = sharedPreferences.getBoolean("STYLE",false);//true sketch,false sprint
        workingday[0] = sharedPreferences.getBoolean("day0", false);//sun
        workingday[1] = sharedPreferences.getBoolean("day1",false);//mon
        workingday[2] = sharedPreferences.getBoolean("day2",false);//tue
        workingday[3] = sharedPreferences.getBoolean("day3",false);//wed
        workingday[4] = sharedPreferences.getBoolean("day4",false);//thu
        workingday[5] = sharedPreferences.getBoolean("day5",false);//fri
        workingday[6] = sharedPreferences.getBoolean("day6",false);//sat
    }

    public void update(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ACTIVATE",activate);
        editor.putInt("STARTH", startofficetimehr);
        editor.putInt("STARTM", startofficetimemn);
        editor.putInt("STOPH",stopofficetimehr);
        editor.putInt("STOPM",stopofficetimemn);
        editor.putInt("PERIODH",periodHr);
        editor.putInt("PERIODM",periodMn);
        editor.putBoolean("STYLE",exstyle);
        editor.putBoolean("day0", workingday[0]);
        editor.putBoolean("day1", workingday[1]);
        editor.putBoolean("day2",workingday[2]);
        editor.putBoolean("day3",workingday[3]);
        editor.putBoolean("day4",workingday[4]);
        editor.putBoolean("day5",workingday[5]);
        editor.putBoolean("day6",workingday[6]);
        editor.commit();
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public void setExstyle(boolean exstyle) {
        this.exstyle = exstyle;
    }

    public void setPeriodHr(int periodHr) {
        if(periodHr >= 0 && periodHr < 24) {
            this.periodHr = periodHr;
        }
    }

    public void setPeriodMn(int periodMn) {
        if(periodMn >= 0 && periodMn < 59) {
            this.periodMn = periodMn;
        }
    }

    public void setStartofficetimehr(int startofficetimehr) {
        if(startofficetimehr >= 0 && startofficetimehr < 24) {
            this.startofficetimehr = startofficetimehr;
        }
    }

    public void setStartofficetimemn(int startofficetimemn) {
        if(startofficetimemn >= 0 && startofficetimemn < 60) {
            this.startofficetimemn = startofficetimemn;
        }
    }

    public void setStopofficetimehr(int stopofficetimehr) {
        if(stopofficetimehr >= 0 && stopofficetimehr < 24) {
            this.stopofficetimehr = stopofficetimehr;
        }
    }

    public void setStopofficetimemn(int stopofficetimemn) {
        if(stopofficetimemn >= 0 && stopofficetimemn < 60) {
            this.stopofficetimemn = stopofficetimemn;
        }
    }

    public void setWorkingday(boolean[] workingday) {
        this.workingday = workingday;
    }

    public boolean[] getWorkingday() {
        return workingday;
    }

    public int getPeriodHr() {
        return periodHr;
    }

    public int getPeriodMn() {
        return periodMn;
    }

    public int getStartofficetimehr() {
        return startofficetimehr;
    }

    public int getStartofficetimemn() {
        return startofficetimemn;
    }

    public int getStopofficetimehr() {
        return stopofficetimehr;
    }

    public int getStopofficetimemn() {
        return stopofficetimemn;
    }

    public boolean isActivate() {
        return activate;
    }

    public boolean isExstyle() {
        return exstyle;
    }
}
