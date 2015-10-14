package com.example.rxusagi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SetAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        TextView starttime = (TextView) findViewById(R.id.startofficetimetext);
        starttime.setText("8:00");
        TextView endtime = (TextView) findViewById(R.id.endofficetimetext);
        endtime.setText("16:00");
        TextView hrnm = (TextView) findViewById(R.id.hrmn);
        hrnm.setText("minutes");
        TextView numhrnm = (TextView) findViewById(R.id.numhrmn);
        numhrnm.setText("30");
        TextView workingday= (TextView) findViewById(R.id.workingdaytext);
        workingday.setText("Mon,Wed,Fri");
    }
    public void onBackClick(View v){
        finish();
    }
    public void onSettingClicked(View v){
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
    }
}
