package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.AlarmClock;

import java.io.InputStreamReader;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.AlarmClock;
import android.util.Log;

import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class AlarmManagement {
    public AlarmState alarmState;
    public Alarm alarm;
    public Setting setting;
    public AlarmClock alarmClock;
    public static AlarmManagement alarmManagement;
    private Calendar calendar;
    public  AlarmManagement(Context context,InputStreamReader isr,SharedPreferences sh){
        Log.i("TAG2", "AlarmManagementCre");
        alarm = new Alarm(context,isr);
        alarmState = new AlarmState(sh);
        setting = new Setting(sh);
        alarmManagement = this;
       calendar = Calendar.getInstance();
    }

    public static AlarmManagement Instance(){
        return  alarmManagement;
    }

    public long getNextTimeMillisec() {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay ="";
        if (Calendar.MONDAY == dayOfWeek) {
            weekDay = "monday";
        } else if (Calendar.TUESDAY == dayOfWeek) {
            weekDay = "tuesday";
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            weekDay = "wednesday";
        } else if (Calendar.THURSDAY == dayOfWeek) {
            weekDay = "thursday";
        } else if (Calendar.FRIDAY == dayOfWeek) {
            weekDay = "friday";
        } else if (Calendar.SATURDAY == dayOfWeek) {
            weekDay = "saturday";
        } else if (Calendar.SUNDAY == dayOfWeek) {
            weekDay = "sunday";
        }
        if(todayHasnext()) {
            calendar = Calendar.getInstance();
            long i = calendar.get(Calendar.HOUR_OF_DAY) * 3600000 + calendar.get(Calendar.MINUTE) * 60000 + calendar.get(Calendar.SECOND) * 1000;
            i =alarmState.getStartofficetimehr()*3600000 + alarmState.getStartofficetimemn()*60000 - i;
            if(i > 0){
                return i + alarmState.getPeriodHr()*3600000 + alarmState.getPeriodMn()*60000;
            }else{
                i = -i;
                return (alarmState.getPeriodHr()*3600000 + alarmState.getPeriodMn()*60000) - i%(alarmState.getPeriodHr()*3600000 + alarmState.getPeriodMn()*60000);
            }
        }
        return 10;
    }

    public boolean todayHasnext(){
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int weekDay = 0;
        if (Calendar.MONDAY == dayOfWeek) {
            weekDay = 1;
        } else if (Calendar.TUESDAY == dayOfWeek) {
            weekDay = 2;
        } else if (Calendar.WEDNESDAY == dayOfWeek) {
            weekDay = 3;
        } else if (Calendar.THURSDAY == dayOfWeek) {
            weekDay = 4;
        } else if (Calendar.FRIDAY == dayOfWeek) {
            weekDay = 5;
        } else if (Calendar.SATURDAY == dayOfWeek) {
            weekDay = 6;
        } else if (Calendar.SUNDAY == dayOfWeek) {
            weekDay = 0;
        }
        boolean[] bool = alarmState.getWorkingday();
        if(!bool[weekDay]){
            return false;
        }
        Log.i("TAG10",""+weekDay);
        int i = calendar.HOUR_OF_DAY*60 + calendar.MINUTE;
        if(alarmState.getStartofficetimehr()*60+alarmState.getStartofficetimemn() < alarmState.getStopofficetimehr()*60+alarmState.getStartofficetimemn()){
            if(alarmState.getStopofficetimehr()*60+alarmState.getStartofficetimemn()>i){
                return true;
            }
        }else if(alarmState.getStartofficetimehr()*60+alarmState.getStartofficetimemn() > alarmState.getStopofficetimehr()*60+alarmState.getStartofficetimemn()){
            return true;
        }else{
            return true;
        }
        return  false;
    }

}
