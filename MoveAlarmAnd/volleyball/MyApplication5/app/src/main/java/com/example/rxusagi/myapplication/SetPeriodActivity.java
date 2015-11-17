package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.AlarmManagement;

public class SetPeriodActivity extends AppCompatActivity {
    private AlarmManagement alarmManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_period);
        alarmManagement = AlarmManagement.Instance();
    }
    public void onBackClick(View v){
        finish();
    }

    public void onClick30(View v){
        if(canClick(30)) {
            alarmManagement.alarmState.setPeriodHr(0);
            alarmManagement.alarmState.setPeriodMn(30);
            endAct();
        }
    }
    public void onClick40(View v){
        if(canClick(40)) {
            alarmManagement.alarmState.setPeriodHr(0);
            alarmManagement.alarmState.setPeriodMn(40);
            endAct();
        }
    }
    public void onClick50(View v){
        if(canClick(50)) {
            alarmManagement.alarmState.setPeriodHr(0);
            alarmManagement.alarmState.setPeriodMn(50);
            endAct();
        }
    }
    public void onClick100(View v){
        if(canClick(60)) {
            alarmManagement.alarmState.setPeriodHr(1);
            alarmManagement.alarmState.setPeriodMn(0);
            endAct();
        }
    }
    public void onClick110(View v){
        if(canClick(70)) {
            alarmManagement.alarmState.setPeriodHr(1);
            alarmManagement.alarmState.setPeriodMn(10);
            endAct();
        }
    }
    public void onClick120(View v){
        if(canClick(80)) {
            alarmManagement.alarmState.setPeriodHr(1);
            alarmManagement.alarmState.setPeriodMn(20);
            endAct();
        }
    }
    public void onClick130(View v){
        if(canClick(90)) {
            alarmManagement.alarmState.setPeriodHr(1);
            alarmManagement.alarmState.setPeriodMn(30);
            endAct();
        }
    }
    public void onClick200(View v){
        if(canClick(120)) {
            alarmManagement.alarmState.setPeriodHr(2);
            alarmManagement.alarmState.setPeriodMn(0);
            endAct();
        }
    }

    private boolean canClick(int time){
        int timebet;
        int h = alarmManagement.alarmState.getStopofficetimehr() - alarmManagement.alarmState.getStartofficetimehr();
        int m = alarmManagement.alarmState.getStopofficetimemn() - alarmManagement.alarmState.getStartofficetimemn();
        timebet = h*60 + m;
        h = timebet/60;
        m = timebet%60;
        Log.i("a",timebet+"");
        if(time > timebet){
            Toast toast = Toast.makeText(getApplicationContext(),"Period must less than "+h+":"+m/10+m%10,Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        return true;
    }

    private void endAct(){
        finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
        alarmManagement.alarmState.update();
    }
}
