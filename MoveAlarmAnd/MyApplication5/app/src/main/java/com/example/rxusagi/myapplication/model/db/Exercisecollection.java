package com.example.rxusagi.myapplication.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by RXUsagi on 15/10/2015.
 */
public class Exercisecollection extends SQLiteOpenHelper{
    public static String TABLE_NAME = "description";
    public static int TABLE_VERSION = 15;
    public static String TABLE_KEY_ID = "_id";
    public static String TABLE_IMAGE = "img";
    public static String TABLE_INSTRUCTION = "instruction";
    public static String TABLE_INSTRUCTION2 = "instruction2";
    public static String TABLE_INSTRUCTION_NAME = "instructionname";
    public static String TABLE_STYLE = "style";
    public static InputStreamReader streamreader;
    public static SQLiteDatabase sqLiteDatabase;
    public Exercisecollection(Context context,InputStreamReader streamreader) {
        super(context,TABLE_NAME, null,TABLE_VERSION);
        Log.i("TAG2", "ExercisecollectionCre");
        this.streamreader = streamreader;
        Log.i("TAG2", "ExercisecollectionCre2");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG2","CreateTable");
        //if(!isTableExists(db,TABLE_NAME)) {
        sqLiteDatabase = db;
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                    + TABLE_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TABLE_IMAGE + " TEXT, "
                    + TABLE_INSTRUCTION + " TEXT, "
                    + TABLE_INSTRUCTION2 + " TEXT, "
                    + TABLE_INSTRUCTION_NAME + " TEXT "
                    + TABLE_STYLE + " TEXT);");
       // }
        Log.i("TAG2", "CreateTable2");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TAG2","Upgrade");
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
        Log.i("exercisecollection", "Table upgrade from " + oldVersion + " to " + newVersion);
    }

}
