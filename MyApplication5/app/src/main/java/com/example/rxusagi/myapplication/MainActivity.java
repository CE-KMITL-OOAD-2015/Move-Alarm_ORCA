package com.example.rxusagi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView hour10;
    private TextView hour1;
    private TextView hdoubleColon;
    private TextView minute10;
    private TextView minute1;
    private TextView mdoubleColon;
    private TextView second10;
    private TextView second1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindWidget();
        setinit();
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
