package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.User_Friend.User;

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    TextView surname;
    TextView age;
    TextView gender;
    TextView status;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView)findViewById(R.id.profilename);
        surname  = (TextView)findViewById(R.id.profilelastname);
        age = (TextView)findViewById(R.id.profileAge);
        gender = (TextView)findViewById(R.id.profilegender);
        status = (TextView)findViewById(R.id.profilestatus);
        score = (TextView)findViewById(R.id.profilescore);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity mainActivity = MainActivity.instance();
        User user = mainActivity.userManagement.getUser();
        Log.i("user",user.toString());
        Log.i("user",user.getName() + user.getSurname() + user.getAge() + user.getGender() + user.getStatus() + user.getScore());
        name.setText(user.getName());
        surname.setText(user.getSurname());
        age.setText(user.getAge()+"");
        gender.setText(user.getGender());
        status.setText(user.getStatus());
        score.setText(user.getScore()+"");

    }

    public void onBackClick(View v){
        // System.exit(0);
        finish();
    }
}
