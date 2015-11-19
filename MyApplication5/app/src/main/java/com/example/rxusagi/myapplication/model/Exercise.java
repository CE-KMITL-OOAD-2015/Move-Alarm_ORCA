package com.example.rxusagi.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rxusagi.myapplication.model.db.Exercisecollection;
import com.example.rxusagi.myapplication.model.Instruction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class Exercise {
    private Exercisecollection collection;
    private SQLiteDatabase db;
    private ArrayList<Instruction> all;
    private int sketch;
    public Exercise(Context context,InputStreamReader isr){
        Log.i("TAG2","ExerciseCre");
        collection = new Exercisecollection(context,isr);
        Log.i("TAG2","ExerciseCre1");
        db = collection.getWritableDatabase();
        Log.i("TAG2","ExerciseCre2");
        all = getAllInstruction(isr);
        Log.i("TAGC",""+sketch);
        Log.i("TAGC",""+all.size());
        Log.i("TAG2","ExerciseCre3");
    }

    public Instruction randInstruction(String sty){
        Log.i("TAGS","S0");
        Random rand = new Random();
        Log.i("TAGS","S1");
        int ans = rand.nextInt(all.size())-1;
        Log.i("TAGS","S2");
        if((sty.equals("stretch"))&&(sketch==0)){
            Log.i("TAGR","NOT sketch");
            return null;
        }
        else if((sty.equals("sprint"))&&(all.size()-sketch==0)){
            Log.i("TAGR","NOT sprint");
            return null;
        }
        Log.i("TAGN",sty);
        while(!all.get(ans).getStyle().equals(sty)) {
            ans = rand.nextInt(all.size());
            Log.i("TAGR","FOUND "+sty+ans);
            Log.i("TAGR",all.get(ans).getStyle());
        }
        Log.i("TAGS","S3 " + ans);
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
                Log.i("TAGR1",in.getStyle());
                if(in.getStyle().equals("stretch")){
                    Log.i("TAGR2",in.getStyle()+"!");
                    sketch++;
                }
            }
        }catch (Exception e){e.getMessage();}
        /**
        Cursor cursor = db.rawQuery("SELECT * FROM "+Exercisecollection.TABLE_NAME+" WHERE _id != ?",null);
        Log.i("TAG2", "ExerciseCre6" + cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
                Instruction in = new Instruction(
                        cursor.getInt(cursor.getColumnIndex(Exercisecollection.TABLE_KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(Exercisecollection.TABLE_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(Exercisecollection.TABLE_INSTRUCTION)),
                        cursor.getString(cursor.getColumnIndex(Exercisecollection.TABLE_INSTRUCTION2)),
                        cursor.getString(cursor.getColumnIndex(Exercisecollection.TABLE_INSTRUCTION_NAME)),
                        cursor.getString(cursor.getColumnIndex(Exercisecollection.TABLE_STYLE)));
                Log.i("TAG0", "INSDDDD" + in.getInstruction_name());
                if(in.getStyle().equals("sketch")){
                    sketch++;
                }
                allInstruction.add(in);
        }
         **/
        //Log.i("TAG12"," 1 "+sketch + " 2 " + all.size());
        return allInstruction;
    }


    /**
    public void inti (InputStreamReader isd){
        BufferedReader reader = null;
        Log.i("TAG2", "INTI");
        try {
            reader = new BufferedReader(isd);
            String mline = reader.readLine();
            String[] scan;
            while (mline != null) {
                scan = mline.split(",");
                Log.i("TAG3", "INSa" + scan[0] + scan[1] + scan[2] + scan[3]);

                 ContentValues values = new ContentValues();
                 values.put(Exercisecollection.TABLE_IMAGE,scan[0]);
                 values.put(Exercisecollection.TABLE_INSTRUCTION, scan[1]);
                 values.put(Exercisecollection.TABLE_INSTRUCTION2,scan[2]);
                 values.put(Exercisecollection.TABLE_INSTRUCTION_NAME,scan[3]);
                 values.put(Exercisecollection.TABLE_STYLE, scan[4]);
                 Log.i("TAG6", values.toString());
                //db.execSQL("INSERT INTO "+Exercisecollection.TABLE_NAME+" VALUES ("+scan[0]+","+scan[1]+","+scan[2]+","+scan[3]+","+scan[4]);
                db.insert(Exercisecollection.TABLE_NAME,null, values);
                mline = reader.readLine();
            }
        }
        catch (Exception e){
            e.getMessage();
        }
        Log.i("TAG2","INTIF");
    }
     **/
}
