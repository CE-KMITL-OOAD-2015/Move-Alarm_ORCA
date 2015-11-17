package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.AlarmManagement;

public class SettingActivity extends AppCompatActivity {
    SeekBar seekBar;
    Switch aSwitch;
    AlarmManagement alarmManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        alarmManagement = AlarmManagement.Instance();
        seekBar = (SeekBar)findViewById(R.id.soundseek);
        aSwitch = (Switch)findViewById(R.id.vibrationonoff);
        seekBar.setProgress(alarmManagement.setting.getSound());
        aSwitch.setChecked(alarmManagement.setting.getVibration());
    }

    @Override
    protected void onPause() {
        alarmManagement.setting.setSound(seekBar.getProgress());
        alarmManagement.setting.setVibration(aSwitch.isChecked());
        alarmManagement.setting.update();
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
        toast.show();
    }

    public void onBackClick(View v){
        finish();
    }
}
