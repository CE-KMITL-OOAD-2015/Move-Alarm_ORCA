package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.AlarmManagement;

public class StartOfficeActivity extends AppCompatActivity {

    private AlarmManagement alarmManagement;
    private TextView hr;
    private TextView mn;
    private int hrStart;
    private int mnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_office);
        alarmManagement = AlarmManagement.Instance();
        hr = (TextView)findViewById(R.id.hrCID);
        mn = (TextView)findViewById(R.id.mnCID);
        hrStart = alarmManagement.alarmState.getStartofficetimehr();
        mnStart = alarmManagement.alarmState.getStartofficetimemn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        change();
    }

    private void change(){
        String hrs = "";
        String mns = "";
        if(alarmManagement.alarmState.getStartofficetimehr()<10){
            hrs = "0";
        }
        if(alarmManagement.alarmState.getStartofficetimemn()<10){
            mns = "0";
        }
        hr.setText(hrs + alarmManagement.alarmState.getStartofficetimehr());
        mn.setText(mns + alarmManagement.alarmState.getStartofficetimemn());
    }

    public void onHrUp(View v){
        alarmManagement.alarmState.setStartofficetimehr(alarmManagement.alarmState.getStartofficetimehr()+1);
        change();
    }
    public void onHrDown(View v){
        alarmManagement.alarmState.setStartofficetimehr(alarmManagement.alarmState.getStartofficetimehr() - 1);
        change();
    }
    public void onMnUp(View v){
        alarmManagement.alarmState.setStartofficetimemn(alarmManagement.alarmState.getStartofficetimemn() + 1);
        change();
    }
    public void onMnDown(View v){
        alarmManagement.alarmState.setStartofficetimemn(alarmManagement.alarmState.getStartofficetimemn() - 1);
        change();
    }


    public  void submit(View v){
        if(alarmManagement.alarmState.getStopofficetimehr()*60+alarmManagement.alarmState.getStopofficetimemn()-alarmManagement.alarmState.getPeriodHr()*60-alarmManagement.alarmState.getPeriodMn() <
                alarmManagement.alarmState.getStartofficetimehr()*60+alarmManagement.alarmState.getStartofficetimemn()){
            String hrs = "";
            String mns = "";
            int h =alarmManagement.alarmState.getStopofficetimehr()-alarmManagement.alarmState.getPeriodHr();
            int m =alarmManagement.alarmState.getStopofficetimemn()-alarmManagement.alarmState.getPeriodMn();
            if(m < 0){
                m = 60+m;
                h--;
            }
            if(h<10){
                hrs = "0";
            }
            if(m<10){
                mns = "0";
            }
            Toast toast = Toast.makeText(getApplicationContext(),"Start office time must less than "+hrs+h+":"+mns+m,Toast.LENGTH_SHORT);
            toast.show();
        }else {
            alarmManagement.alarmState.update();
            MainActivity.instance().cancel();
            MainActivity.instance().wake();
            finish();
            overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
        }
    }
    public void onBackClick(View v){
        alarmManagement.alarmState.setStartofficetimehr(hrStart);
        alarmManagement.alarmState.setStartofficetimemn(mnStart);
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
