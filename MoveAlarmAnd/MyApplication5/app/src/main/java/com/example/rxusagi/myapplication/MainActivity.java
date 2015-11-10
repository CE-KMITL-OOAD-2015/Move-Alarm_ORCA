package com.example.rxusagi.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.concurrent.TimeUnit;

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
    private CounterClass timer;
    @Override
    protected void onStart() {
        super.onStart();
        mainActivity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        count();
    }

    private void count(){
        if(alarmManagement.getNextTimeMillisec()!=0) {
            timer = new CounterClass(alarmManagement.getNextTimeMillisec(), 1000);
            timer.start();
        }
    }
    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
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
        hour1.setText("0");
        hdoubleColon.setText(":");
        minute10.setText("0");
        minute1.setText("0");
        mdoubleColon.setText(":");
        second10.setText("0");
        second1.setText("0");
    }

    public void onSetAlarmClicked(View v){
        startActivity(new Intent(getApplicationContext(),SetAlarmActivity.class));
    }


    public void onSettingClicked(View v){
        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long mil = millisUntilFinished;
            hour10.setText(""+(TimeUnit.MILLISECONDS.toHours(mil)/10));
            hour1.setText(""+(TimeUnit.MILLISECONDS.toHours(mil)%10));
            minute10.setText(""+(TimeUnit.MILLISECONDS.toMinutes(mil)-TimeUnit.MILLISECONDS.toHours(mil)*60)/10);
            minute1.setText(""+(TimeUnit.MILLISECONDS.toMinutes(mil)-TimeUnit.MILLISECONDS.toHours(mil)*60)%10);
            second10.setText(""+(TimeUnit.MILLISECONDS.toSeconds(mil)-TimeUnit.MILLISECONDS.toMinutes(mil)*60)/10);
            second1.setText(""+(TimeUnit.MILLISECONDS.toSeconds(mil)-TimeUnit.MILLISECONDS.toMinutes(mil)*60)%10);
        }

        @Override
        public void onFinish() {
            hour10.setText("X");
            hour1.setText("X");
            minute10.setText("X");
            minute1.setText("X");
            second10.setText("X");
            second1.setText("X");
            count();
        }
    }

}
