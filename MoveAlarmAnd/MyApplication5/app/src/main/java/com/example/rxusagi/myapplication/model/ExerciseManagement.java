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
    public ExerciseManagement(Context context,InputStreamReader isr){
        Log.i("TAG2", "ExerciseManagementCre");
        ex = new Exercise(context,isr);
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
        Random rand = new Random();
        score = rand.nextInt(10) + 25;
        instruction = ex.randInstruction(sty);
    }

}
