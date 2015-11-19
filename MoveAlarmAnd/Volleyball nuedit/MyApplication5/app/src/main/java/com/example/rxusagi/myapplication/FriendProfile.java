package com.example.rxusagi.myapplication;

import android.app.Dialog;
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
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.User_Friend.Friend;
import com.example.rxusagi.myapplication.model.User_Friend.FriendManagement;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

public class FriendProfile extends AppCompatActivity {
    TextView header;
    Friend friend;
    TextView name;
    TextView surname;
    TextView age;
    TextView score;
    TextView gender;
    TextView status;
    ImageView imageView;
    Dialog wait;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        friend = FriendManagement.instance().getFriend(FriendManagement.instance().nextfbId);
        header = (TextView)findViewById(R.id.header);
        name = (TextView)findViewById(R.id.profilename);
        surname = (TextView)findViewById(R.id.profilelastname);
        age= (TextView)findViewById(R.id.profileAge);
        score = (TextView)findViewById(R.id.profilescore);
        gender = (TextView)findViewById(R.id.profilegender);
        status = (TextView)findViewById(R.id.profilestatus);
        imageView = (ImageView)findViewById(R.id.friendprofile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!UserManagement.stateguest) {
            if (friend != null) {
                header.setText(friend.getName() + " " + friend.getSurname());
                name.setText(friend.getName());
                surname.setText(friend.getSurname());
                age.setText(friend.getAge() + "");
                score.setText(friend.getScore() + "");
                gender.setText(friend.getGender());
                status.setText(friend.getStatus());
                wait = new Dialog(FriendProfile.this);
                wait.requestWindowFeature(Window.FEATURE_NO_TITLE);
                wait.setContentView(R.layout.waitdialog);
                wait.setCancelable(false);
                wait.show();
                Transfer transfer = new Transfer();
                transfer.loadImage(friend.getUrl(), imageView, wait);
            } else {
                Log.i("List Array", "Null FBID ERROR");
            }
        }
    }

    public void onBackClick(View v){
        // System.exit(0);
        finish();
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}
