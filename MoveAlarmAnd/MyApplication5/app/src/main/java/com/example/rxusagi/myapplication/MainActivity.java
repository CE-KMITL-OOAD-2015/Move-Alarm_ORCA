package com.example.rxusagi.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.AlarmManagement;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStreamReader;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity;
    public AlarmManagement alarmManagement;
    protected TextView textview;
    private TextView hour10;
    private TextView hour1;
    private TextView hdoubleColon;
    private TextView minute10;
    private TextView minute1;
    private TextView mdoubleColon;
    private TextView second10;
    private TextView second1;
    @Override
    protected void onStart() {
        super.onStart();
        mainActivity = this;
    }

    public  MainActivity instance(){
        return mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();
        setinit();
        try {
            alarmManagement = new AlarmManagement(this,new InputStreamReader(getAssets().open("instruction.CSV"),"UTF-8"),getSharedPreferences("MOVEALARM_PREFERENCE", Context.MODE_PRIVATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void bindWidget(){
        hour10 = (TextView) findViewById(R.id.hournumber10);
        hour1 = (TextView) findViewById(R.id.hournumber1);
        hdoubleColon = (TextView) findViewById(R.id.hdoublecolon);
        minute10 = (TextView) findViewById(R.id.minutenumber10);
        minute1 = (TextView) findViewById(R.id.minutenumber1);
        mdoubleColon = (TextView) findViewById(R.id.mdoublecolon);
        second10 = (TextView) findViewById(R.id.secondnumber10);
        second1 = (TextView) findViewById(R.id.secondnumber1);
    }

    protected void setinit(){
        hour10.setText("0");
        hour1.setText("1");
        hdoubleColon.setText(":");
        minute10.setText("2");
        minute1.setText("3");
        mdoubleColon.setText(":");
        second10.setText("4");
        second1.setText("5");
    }

    public void onSetAlarmClicked(View v){
        startActivity(new Intent(getApplicationContext(),SetAlarmActivity.class));
    }


    public void onSettingClicked(View v){
        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
    }

}
