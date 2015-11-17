package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;

import com.example.rxusagi.myapplication.model.AlarmManagement;

public class WorkingDay extends AppCompatActivity {


    private Switch sun;
    private Switch mon;
    private Switch tue;
    private Switch wed;
    private Switch thu;
    private Switch fri;
    private Switch sat;
    private AlarmManagement alarmManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_day);
        alarmManagement = AlarmManagement.Instance();
        sun = (Switch)findViewById(R.id.sunsw);
        mon = (Switch)findViewById(R.id.monsw);
        tue = (Switch)findViewById(R.id.tuesw);
        wed = (Switch)findViewById(R.id.wedsw);
        thu = (Switch)findViewById(R.id.thusw);
        fri = (Switch)findViewById(R.id.frisw);
        sat = (Switch)findViewById(R.id.satsw);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean[] working = alarmManagement.alarmState.getWorkingday();
        sun.setChecked(working[0]);
        mon.setChecked(working[1]);
        tue.setChecked(working[2]);
        wed.setChecked(working[3]);
        thu.setChecked(working[4]);
        fri.setChecked(working[5]);
        sat.setChecked(working[6]);
    }

    @Override
    protected void onPause() {
        boolean[] working = new boolean[7];
        working[0] = sun.isChecked();
        working[1] = mon.isChecked();
        working[2] = tue.isChecked();
        working[3] = wed.isChecked();
        working[4] = thu.isChecked();
        working[5] = fri.isChecked();
        working[6] = sat.isChecked();
        alarmManagement.alarmState.setWorkingday(working);
        MainActivity.instance().cancel();
        MainActivity.instance().wake();
        super.onPause();
    }

    public void onBackClick(View v){
        finish();
    }
}
