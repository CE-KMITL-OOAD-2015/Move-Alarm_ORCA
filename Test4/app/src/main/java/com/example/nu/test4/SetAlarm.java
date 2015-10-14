package com.example.nu.test4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
public class SetAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_alarm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickstartoffice(View v){
        startActivity(new Intent(getApplicationContext(), StartOfficeActivity.class));
    }
    public void onClickendoffice(View v){
        startActivity(new Intent(getApplicationContext(), EndOfficeActivity.class));
    }
    public void onClicksetperiod(View v){
        startActivity(new Intent(getApplicationContext(),SetPeriodActivity.class));
    }
    public void onClickworkingday(View v){
        startActivity(new Intent(getApplicationContext(),WorkingdayActivity.class));
    }
}
