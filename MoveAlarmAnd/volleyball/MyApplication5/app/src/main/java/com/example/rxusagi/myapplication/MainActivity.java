package com.example.rxusagi.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.rxusagi.myapplication.model.AlarmManagement;
import com.example.rxusagi.myapplication.model.InstructionReceiver;
import com.example.rxusagi.myapplication.model.User_Friend.User;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity;
    public AlarmManagement alarmManagement;
    public UserManagement userManagement;
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
    private AlarmManager alarmManager;
    private ConnectivityManager connectivityManager;
    private boolean connected;
    private Dialog wait;
    public static MainActivity instance(){
        return mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManagement = new UserManagement(getSharedPreferences("MOVEALARM_PREFERENCE", Context.MODE_PRIVATE));
        try {
            alarmManagement = new AlarmManagement(new InputStreamReader(getAssets().open("instruction.CSV"),"UTF-8"),getSharedPreferences("MOVEALARM_PREFERENCE", Context.MODE_PRIVATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        bindWidget();
    }
    @Override
    protected void onStart() {
        super.onStart();
        setinit();
        mainActivity = this;
        if(Transfer.isOnline(getApplicationContext())){
            Log.i("NET", "ONLINE" + UserManagement.instance().wantLogout);
            if ((User.instance() == null )&&(UserManagement.stateguest!=true)){
                Log.i("NET", "ONLINE2");
                UserManagement.instance().createUser();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        }else{
            if(UserManagement.stateguest==false) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.connectdialog);
                dialog.setCancelable(false);
                RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.okDialog);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                dialog.show();
            }
        }
        if(UserManagement.stateguest){
            UserManagement.instance().createUser();
            cancelWait();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        count();
    }
    @Override
    protected void onPause() {
        if(timer != null) {
            timer.cancel();
        }
        super.onPause();
        SharedPreferences sh = getSharedPreferences("MOVEALARM_PREFERENCE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putBoolean("stateguest", UserManagement.stateguest);
        editor.commit();
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
    private void count(){
        if(alarmManagement.hasDaytoactivate()) {
            timer = new CounterClass(alarmManagement.findNextTimeMillisec()+10, 1000);
            timer.start();
            wake();
        }
    }
    public void wake(){
        if(alarmManagement.alarmState.isActivate()) {
            Intent intent = new Intent(MainActivity.this, InstructionReceiver.class);
            PendingIntent send = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
            Long time = SystemClock.elapsedRealtime();
            time += alarmManagement.findNextTimeMillisec();
            if (alarmManagement.findNextTimeMillisec() != -1) {
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, send);
            }
        }
    }

    public void cancelWait(){
        if(wait != null){
            wait.cancel();
        }
    }
    public void onSetAlarmClicked(View v){
        startActivity(new Intent(getApplicationContext(), SetAlarmActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void onSettingClicked(View v){
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void onAboutClick(View v){
        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    public void onEventClick(View v){
        startActivity(new Intent(getApplicationContext(), EventActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void onProfileClick(View v){
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void onFriendClick(View v){
        Transfer transfer = new Transfer();
        transfer.downloadUserInfo();
        startActivity(new Intent(getApplicationContext(), FriendActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void onHowtouseClicked(View v){
        Transfer transfer = new Transfer();
        transfer.loadyoutubelink();
    }

    public void playhowtouse(String url){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
    public void cancel(){
        if(timer != null){
            timer.cancel();
        }
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long mil = millisUntilFinished;
            if(TimeUnit.MILLISECONDS.toSeconds(mil) <= 360000) {
                hour10.setText("" + (TimeUnit.MILLISECONDS.toHours(mil) / 10));
                hour1.setText("" + (TimeUnit.MILLISECONDS.toHours(mil) % 10));
                minute10.setText("" + (TimeUnit.MILLISECONDS.toMinutes(mil) - TimeUnit.MILLISECONDS.toHours(mil) * 60) / 10);
                minute1.setText("" + (TimeUnit.MILLISECONDS.toMinutes(mil) - TimeUnit.MILLISECONDS.toHours(mil) * 60) % 10);
                second10.setText("" + (TimeUnit.MILLISECONDS.toSeconds(mil) - TimeUnit.MILLISECONDS.toMinutes(mil) * 60) / 10);
                second1.setText("" + (TimeUnit.MILLISECONDS.toSeconds(mil) - TimeUnit.MILLISECONDS.toMinutes(mil) * 60) % 10);
            }else{
                hour10.setText("-");
                hour1.setText("-");
                minute10.setText("-");
                minute1.setText("-");
                second10.setText("-");
                second1.setText("-");
            }
        }
        @Override
        public void onFinish() {
            hour10.setText("-");
            hour1.setText("-");
            minute10.setText("-");
            minute1.setText("-");
            second10.setText("-");
            second1.setText("-");
            count();
        }
    }
}
