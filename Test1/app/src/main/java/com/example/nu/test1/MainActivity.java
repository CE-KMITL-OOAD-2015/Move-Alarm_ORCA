package com.example.nu.test1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Cycle","Create");
    }
    @Override
    protected void onStart() {
        Log.d("Cycle","Start");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d("Cycle","Resume");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d("Cycle","Pause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("Cycle","Stop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Cycle","Destroy");
        super.onDestroy();
    }

    public void npcNu(View v){
        finish();
    }

}
