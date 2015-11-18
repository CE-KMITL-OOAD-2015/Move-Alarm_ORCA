package com.example.rxusagi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.AlarmManagement;

public class SetAlarmActivity extends AppCompatActivity {
    private AlarmManagement alarmManagement;
    private TextView starttime;
    private TextView endtime;
    private TextView hrnm;
    private TextView workingday;
    private Switch act;
    private Switch stysw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        alarmManagement = AlarmManagement.Instance();
        starttime = (TextView) findViewById(R.id.startofficetimetext);
        endtime = (TextView) findViewById(R.id.endofficetimetext);
        hrnm = (TextView) findViewById(R.id.hrmn);
        //TextView numhrnm = (TextView) findViewById(R.id.numhrmn);
        workingday= (TextView) findViewById(R.id.workingdaytext);
        act = (Switch)findViewById(R.id.activateswitch);
        stysw = (Switch)findViewById(R.id.styleswitch);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String mn = "";
        if(alarmManagement.alarmState.getStopofficetimemn()<10){
            mn = "0";
        }
        endtime.setText(alarmManagement.alarmState.getStopofficetimehr() + " : " + mn + alarmManagement.alarmState.getStopofficetimemn());
        mn = "";
        if(alarmManagement.alarmState.getStartofficetimemn()<10){
            mn = "0";
        }
        starttime.setText(alarmManagement.alarmState.getStartofficetimehr()+" : "+ mn +alarmManagement.alarmState.getStartofficetimemn());
        act.setChecked(alarmManagement.alarmState.isActivate());
        stysw.setChecked(alarmManagement.alarmState.isExstyle());
        String hour = " hour";
        String min = "";
        if(alarmManagement.alarmState.getPeriodHr() > 1){
            hour = hour + "s";
        }
        if(alarmManagement.alarmState.getPeriodMn() < 10){
            min = "0";
        }
        if(alarmManagement.alarmState.getPeriodHr()==0) {
            hrnm.setText(min + alarmManagement.alarmState.getPeriodMn() + " minutes");
        }else if(alarmManagement.alarmState.getPeriodMn() == 0){
            hrnm.setText(alarmManagement.alarmState.getPeriodHr() + hour);
        }else{
            hrnm.setText(alarmManagement.alarmState.getPeriodHr() + hour + " " + min + alarmManagement.alarmState.getPeriodMn() + " minutes");
        }
        boolean[] week = alarmManagement.alarmState.getWorkingday();
        String weekall = "";
        if(week[0]){
            weekall = weekall + " Sun";
        }
        if(week[1]){
            weekall = weekall + " Mon";
        }
        if(week[2]){
            weekall = weekall + " Tue";
        }
        if(week[3]){
            weekall = weekall + " Wed";
        }
        if(week[4]){
            weekall = weekall + " Thu";
        }
        if(week[5]){
            weekall = weekall + " Fri";
        }
        if(week[6]){
            weekall = weekall + " Sat";
        }
        if(weekall.equals("")){
            weekall = "  -     ";
        }
        workingday.setText(weekall);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onBackClick(View v){
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void onSettingClicked(View v){
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void onClickWorking(View v){
        startActivity(new Intent(getApplicationContext(), WorkingDay.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void onSetPeriod(View v){
        startActivity(new Intent(getApplicationContext(), SetPeriodActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public  void onStartOfficeTime(View v){
        startActivity(new Intent(getApplicationContext(), StartOfficeActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public  void onEndOfficeTime(View v){
        startActivity(new Intent(getApplicationContext(), EndOfficeActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void onSty(View v){
        String text;
        if(stysw.isChecked()){
            text = "Stretch Style";
        }else{
            text = "Sprint Style";
        }
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
        alarmManagement.alarmState.setExstyle(stysw.isChecked());
        alarmManagement.alarmState.update();
    }

    public void onAct(View v){
        String text;
        if(act.isChecked()){
            text = "Active";
            MainActivity.instance().wake();
        }else{
            text = "Inactive";
            MainActivity.instance().cancel();
        }
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
        alarmManagement.alarmState.setActivate(act.isChecked());
        alarmManagement.alarmState.update();
    }
}
