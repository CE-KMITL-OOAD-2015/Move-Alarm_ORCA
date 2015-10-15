package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.util.Log;

import java.io.InputStreamReader;


public class Alarm {
    private ExerciseManagement exerciseManagement;
    public Alarm(Context context,InputStreamReader isr){
        Log.i("TAG2", "AlarmCre");
        exerciseManagement = new ExerciseManagement(context,isr);
    }
    public void startAlarm(){

    }
    public void stopAlarm(){

    }
}
