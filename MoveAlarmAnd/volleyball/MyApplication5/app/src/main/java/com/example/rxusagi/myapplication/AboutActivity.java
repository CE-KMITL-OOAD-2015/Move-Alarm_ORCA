package com.example.rxusagi.myapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class AboutActivity extends AppCompatActivity {
    TextView size;
    TextView versiont;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        size = (TextView)findViewById(R.id.textView5);
        versiont = (TextView)findViewById(R.id.textView4);
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            versiont.setText("Version : " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        try {
            long appsize = getApkSize(getApplicationContext(),getPackageName());
            double appsizef = appsize/1000000.0;
            Log.i("app size",""+appsize + " " + appsizef + getPackageName());
            String a = String.format("Size : %.2f MB", appsizef);
            Log.i("app",a);
            size.setText(a);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void onBackClick(View v){
       // System.exit(0);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public static long getApkSize(Context context, String packageName)
            throws PackageManager.NameNotFoundException {
        return new File(context.getPackageManager().getApplicationInfo(
                packageName, 0).publicSourceDir).length();
    }
}
