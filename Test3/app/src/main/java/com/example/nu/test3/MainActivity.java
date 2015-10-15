package com.example.nu.test3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.int id = item.getItemId();
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:{
                Intent intkt = new Intent(MainActivity.this, Setting.class);
                startActivity(intkt);
                return true;

            }
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onClickstartoffice(View v){
        startActivity(new Intent(getApplicationContext(),StartOfficeActivity.class));
    }
    public void onClickendoffice(View v){
        startActivity(new Intent(getApplicationContext(),EndOfficeActivity.class));
    }
    public void onClicksetperiod(View v){
        startActivity(new Intent(getApplicationContext(),SetPeriodActivity.class));
    }
   /* public void onClickworkingday(View v){
        startActivity(new Intent(getApplicationContext(),WorkingDayActivity.class));
    }*/
}
