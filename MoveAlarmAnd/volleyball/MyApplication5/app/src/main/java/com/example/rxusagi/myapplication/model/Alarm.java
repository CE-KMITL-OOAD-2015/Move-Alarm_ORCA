package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.util.Log;

import java.io.InputStreamReader;


public class Alarm {
    private ExerciseManagement exerciseManagement;
    public Alarm(InputStreamReader isr){
        Log.i("TAG2", "AlarmCre");
        exerciseManagement = new ExerciseManagement(isr);
    }
    public Instruction startAlarm(String sty){
        Log.i("TAGS","ST");
        exerciseManagement.randominstruction(sty);
        Log.i("TAGS","ST1");
        return  exerciseManagement.getInstruction();
    }

    public ExerciseManagement getExerciseManagement(){
        return exerciseManagement;
    }
    public void stopAlarm(){

    }
}
