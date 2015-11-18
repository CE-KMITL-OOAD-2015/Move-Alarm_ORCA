package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.util.Log;

import com.example.rxusagi.myapplication.model.Exercise;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class ExerciseManagement {
    private Exercise ex;
    private Instruction instruction;
    private int score;
    public ExerciseManagement(InputStreamReader isr){
        Log.i("TAG2", "ExerciseManagementCre");
        ex = new Exercise(isr);
        score = 0;
        instruction = null;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public int getScore() {
        return score;
    }

    public void randominstruction(String sty){
        Log.i("TAGS","ST11");
        Random rand = new Random();
        Log.i("TAGS","ST12");
        score = rand.nextInt(10) + 25;
        Log.i("TAGS","ST13");
        instruction = ex.randInstruction(sty);
        Log.i("TAGS","ST14");
    }

}
