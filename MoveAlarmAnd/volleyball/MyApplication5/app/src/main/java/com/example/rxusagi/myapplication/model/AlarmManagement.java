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
    public  AlarmManagement(InputStreamReader isr,SharedPreferences sh){
        Log.i("TAG2", "AlarmManagementCre");
        alarm = new Alarm(isr);
        alarmState = new AlarmState(sh);
        setting = new Setting(sh);
        alarmManagement = this;
        calendar = Calendar.getInstance();
        Log.i("TAG2", "AlarmManagementCreFin");
    }

    public static AlarmManagement Instance(){
        return  alarmManagement;
    }

    public long getNextTimeMillisec() {
        int dayOfWeek = today();
        long i = dayOfWeek*24*60*60*1000 + calendar.get(Calendar.HOUR_OF_DAY) * 60*60*1000 + calendar.get(Calendar.MINUTE) * 60*1000 + calendar.get(Calendar.SECOND) * 1000;
        boolean a[] = alarmState.getWorkingday();
        if(hasDaytoactivate()){
            int index = 0;
            long nexttime = 0;
            boolean result = true;
            while(true){
                if(a[index%7]){
                    nexttime = index*24*60*60*1000 + alarmState.getStopofficetimehr()*60*60*1000 + alarmState.getStopofficetimemn()*60*1000 - i;
                }
                if(nexttime > 0){
                    nexttime = index*24*60*60*1000 + alarmState.getStartofficetimehr()*60*60*1000 + alarmState.getStartofficetimemn()*60*1000 - i;
                    while(true){
                        nexttime = nexttime +  + alarmState.getPeriodHr()*60*60*1000 + alarmState.getPeriodMn()*60*1000;
                        if(nexttime > 0){
                            return nexttime + 10;
                        }
                    }
                }
                index++;
            }
        }else{
            return -1;
        }
    }

    public int today(){
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
        return  weekDay;
    }

    public boolean hasDaytoactivate(){
        boolean a[] = alarmState.getWorkingday();
        return a[0]||a[1]||a[2]||a[3]||a[4]||a[5]||a[6];
    }

}
