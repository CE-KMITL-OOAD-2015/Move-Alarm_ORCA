package com.example.rxusagi.myapplication.model;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class Exercise {
    private ArrayList<Instruction> all;
    private int sketch;
    public Exercise(InputStreamReader isr){
        all = getAllInstruction(isr);
    }

    public Instruction randInstruction(String sty){
        Random rand = new Random();
        int ans = rand.nextInt(all.size())-1;
        if((sty.equals("stretch"))&&(sketch==0)){
            return null;
        }
        else if((sty.equals("sprint"))&&(all.size()-sketch==0)){
            return null;
        }
        while(!all.get(ans).getStyle().equals(sty)) {
            ans = rand.nextInt(all.size());
        }
        return all.get(ans);
    }

    private ArrayList<Instruction> getAllInstruction(InputStreamReader isr) {
        ArrayList<Instruction> allInstruction = new ArrayList<Instruction>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(isr);
            String mline = reader.readLine();
            String[] scan;
            int a = 0;
            sketch = 0;
            while (mline != null) {
                scan = mline.split(",,");
                Instruction in = new Instruction(a,scan[0],scan[1],scan[2],scan[3],scan[4]);
                a++;
                allInstruction.add(in);
                mline = reader.readLine();
                if(in.getStyle().equals("stretch")){
                    sketch++;
                }
            }
        }catch (Exception e){e.getMessage();}
        return allInstruction;
    }

}
