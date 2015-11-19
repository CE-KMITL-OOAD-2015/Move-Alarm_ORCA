package com.example.rxusagi.myapplication;

import android.app.Dialog;
import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.Counter;

public class Accept_Reject extends AppCompatActivity {
    Ringtone ringtone;
    Vibrator vibrator;
    ImageView imageView;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MainActivity.instance().alarmManagement.setting.getSound()) {
            ringtone = RingtoneManager.getRingtone(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
            ringtone.play();
        }
        if(MainActivity.instance().alarmManagement.setting.getVibration()){
            vibrator = (Vibrator) this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
            long pattern[] = {0,100,1000};
            vibrator.vibrate(pattern, 0);
        }
        /**
        dialog = new Dialog(Accept_Reject.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.accept_reject_dialog);
        dialog.setCancelable(true);
        TextView accept = (TextView)dialog.findViewById(R.id.accept);
        TextView reject = (TextView)dialog.findViewById(R.id.reject);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ReceiverInstruction.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        dialog.show();
         **/
        Counter countd = new Counter(10000,1000,this);
        countd.start();


        setContentView(R.layout.activity_accept__reject);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        Drawable drawable = wallpaperManager.peekDrawable();
        imageView = (ImageView)findViewById(R.id.wallpaper);
        imageView.setImageDrawable(drawable);

    }

    @Override
    protected void onPause() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onPause();
    }


    public void onaccept(View v){
        startActivity(new Intent(getApplicationContext(), ReceiverInstruction.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    public void onreject(View v){
        finish();
    }


    @Override
    public void finish() {
        if (MainActivity.instance().alarmManagement.setting.getSound()) {
            ringtone.stop();
        }
        if(MainActivity.instance().alarmManagement.setting.getVibration()) {
            vibrator.cancel();
        }
        super.finish();
    }


}
