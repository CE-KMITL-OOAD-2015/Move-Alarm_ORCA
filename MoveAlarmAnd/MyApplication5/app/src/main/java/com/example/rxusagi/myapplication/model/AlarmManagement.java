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

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class AlarmManagement {
    public AlarmState alarmState;
    public Alarm alarm;
    public Setting setting;
    public AlarmClock alarmClock;
    public static AlarmManagement alarmManagement;
    public  AlarmManagement(Context context,InputStreamReader isr,SharedPreferences sh){
        Log.i("TAG2", "AlarmManagementCre");
        alarm = new Alarm(context,isr);
        alarmState = new AlarmState(sh);
        setting = new Setting(sh);
        alarmManagement = this;
    }

    public static AlarmManagement Instance(){
        return  alarmManagement;
    }
}
