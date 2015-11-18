package com.example.rxusagi.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.User_Friend.User;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

import java.io.IOException;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    TextView surname;
    TextView age;
    TextView gender;
    TextView status;
    TextView score;
    ImageView picture;
    RelativeLayout statuseditlayout;
    Dialog wait;
    public static ProfileActivity profileActivity;
    private ConnectivityManager connectivityManager;
    private boolean connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView) findViewById(R.id.profilename);
        surname = (TextView) findViewById(R.id.profilelastname);
        age = (TextView) findViewById(R.id.profileAge);
        gender = (TextView) findViewById(R.id.profilegender);
        status = (TextView) findViewById(R.id.profilestatus);
        score = (TextView) findViewById(R.id.profilescore);
        picture = (ImageView) findViewById(R.id.profilepicture);
        statuseditlayout = (RelativeLayout)findViewById(R.id.statuseditLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        update();
    }

    public static ProfileActivity instance() {
        return profileActivity;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        profileActivity = null;
    }

    public void update(){
        if(!UserManagement.isguest) {
            Log.i("FBIDUPDATE", UserManagement.instance().getUser().getFacebookID() + "");
            profileActivity = this;
            updateInfo();
            Transfer transfer = new Transfer();
            transfer.getUserInfo();
            if (UserManagement.instance().getUser().getPrimaryKey() != -1) {
                statuseditlayout.setClickable(false);
                wait = new Dialog(ProfileActivity.this);
                wait.requestWindowFeature(Window.FEATURE_NO_TITLE);
                wait.setContentView(R.layout.waitdialog);
                wait.show();
            }
        }else{
            clearInfo();
        }
    }

    public void updateInfo() {
        User user = User.instance();
        Log.i("user", user.toString());
        Log.i("user", user.getName() + user.getSurname() + user.getAge() + user.getGender() + user.getStatus() + user.getScore());
        if (user.getPrimaryKey() != -1) {
            name.setText(user.getName());
            surname.setText(user.getSurname());
            age.setText(user.getAge() + "");
            gender.setText(user.getGender());
            status.setText(user.getStatus());
            score.setText(user.getScore() + "");
            String picurl = user.getUrl();
            Log.i("PICurl", picurl);
            Transfer transfer = new Transfer();
            transfer.loadImage(picurl,picture);
        }
        statuseditlayout.setClickable(true);
        if(wait != null) {
            wait.cancel();
        }
    }

    private void clearInfo(){
        name.setText("");
        surname.setText("");
        age.setText("");
        gender.setText("");
        status.setText("");
        score.setText("");
        Drawable drawable = getResources().getDrawable(R.mipmap.profile_icon);
        picture.setImageDrawable(drawable);
    }

    public void onLogout(View v){
        Log.i("NET", "LOGOUT");
        if(Transfer.isOnline(getApplicationContext())) {
            if (!UserManagement.isguest) {
                UserManagement.willLogout();
            }
            UserManagement.isguest = false;
            UserManagement.instance().createUser();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else {
            Toast.makeText(getApplicationContext(),"Please Connect to Internet",Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackClick(View v){
        // System.exit(0);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onEditClicked(View v){
        if(!UserManagement.isguest) {
            startActivity(new Intent(getApplicationContext(), EditProfile.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

}
