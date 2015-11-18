package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.AlarmManagement;

public class SettingActivity extends AppCompatActivity {
    Switch sSwitch;
    Switch aSwitch;
    AlarmManagement alarmManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        alarmManagement = AlarmManagement.Instance();
        sSwitch = (Switch)findViewById(R.id.soundseek);
        aSwitch = (Switch)findViewById(R.id.vibrationonoff);
        sSwitch.setChecked(alarmManagement.setting.getSound());
        aSwitch.setChecked(alarmManagement.setting.getVibration());
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    public void onVib(View v){
        String text;
        if(aSwitch.isChecked()){
            text = "Vibration On";
        }else{
            text = "Vibration Off";
        }
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        alarmManagement.setting.setVibration(aSwitch.isChecked());
        alarmManagement.setting.update();
        toast.show();
    }

    public void onVibs(View v){
        String text;
        if(sSwitch.isChecked()){
            text = "Sound On";
        }else{
            text = "Sound Off";
        }
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        alarmManagement.setting.setSound(sSwitch.isChecked());
        alarmManagement.setting.update();
        toast.show();
    }

    public void onBackClick(View v){
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}
