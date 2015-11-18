package com.example.rxusagi.myapplication;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

public class Accept_Reject extends AppCompatActivity {
    RelativeLayout relativeLayout;
    Ringtone ringtone;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept__reject);
        relativeLayout = (RelativeLayout)findViewById(R.id.wallpaper);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable drawable = wallpaperManager.getDrawable();
        relativeLayout.setBackground(drawable);
        Countd countd = new Countd(10000,1000);
        countd.start();
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
        ringtone.play();
        MainActivity.instance().wake();
        if(MainActivity.instance().alarmManagement.setting.getVibration()){
            vibrator = (Vibrator) this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
            long pattern[] = {0,100,1000};
            vibrator.vibrate(pattern,0);
        }
    }

    public void onaccept(View v){
        startActivity(new Intent(getApplicationContext(), ReceiverInstruction.class));
        finish();
    }

    public void onreject(View v){
        finish();
    }

    @Override
    public void finish() {
        ringtone.stop();
        if(MainActivity.instance().alarmManagement.setting.getVibration()) {
            vibrator.cancel();
        }
        super.finish();
    }

    public class Countd extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Countd(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            finish();
        }
    }
}
