package com.example.rxusagi.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.FriendAdapter;
import com.example.rxusagi.myapplication.model.User_Friend.FriendManagement;
import com.example.rxusagi.myapplication.model.User_Friend.User;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

import java.util.ArrayList;


public class FriendActivity extends AppCompatActivity {
    ListView listView;
    public static FriendActivity friendActivity;
    Dialog wait;

    public static FriendActivity instance(){
        return  friendActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        listView = (ListView)findViewById(R.id.scoreboarda);
    }

    @Override
    protected void onStart() {
        super.onStart();
        friendActivity = this;
        if(!UserManagement.stateguest) {
            Transfer transfer = new Transfer();
            transfer.findFriendProfile(this);
        }else {
            Toast.makeText(this,"Please Login",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void waitDialog(){
        wait = new Dialog(FriendActivity.this);
        wait.requestWindowFeature(Window.FEATURE_NO_TITLE);
        wait.setContentView(R.layout.waitdialog);
        wait.setCancelable(false);
        wait.show();
    }

    public void cancelDialog(){
        if(wait != null){
            wait.cancel();
        }
    }

    public void setListView(){
        int max = UserManagement.friendManagement.friendlist.size();
        //ArrayList<String> fbfriend = new ArrayList<String>();
        ImageView myImage = (ImageView)findViewById(R.id.myImage);
        TextView name = (TextView)findViewById(R.id.name);
        TextView status = (TextView)findViewById(R.id.status);
        TextView score = (TextView)findViewById(R.id.score);
        Transfer tranfer = new Transfer();
        tranfer.loadImage(UserManagement.user.getUrl(),myImage);
        name.setText(UserManagement.user.getName() + " " + UserManagement.user.getSurname());
        status.setText(UserManagement.user.getStatus()+"");
        score.setText(UserManagement.user.getScore()+"");
        String fbfriend[] = new String[max];
        int index = 0;
        while(index < max){
            fbfriend[index]=UserManagement.friendManagement.friendlist.get(index).getFacebookID();
            Log.i("ListFriends", fbfriend[index]);
            index++;
        }
        listView.setAdapter(new FriendAdapter(fbfriend,FriendActivity.this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendManagement.nextfbId = position;
                Log.i("ListPosition",position+" "+FriendManagement.nextfbId);

                startActivity(new Intent(getApplicationContext(), FriendProfile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        cancelDialog();
    }

    public void onBackClick(View v){
        //setListView();
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void onMorefriend(View v){
        if(Transfer.isOnline(getApplicationContext())) {
            if (!UserManagement.stateguest) {
                FriendManagement.wantmorefriend = true;
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            } else {
                Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"Please Connenct to Internet",Toast.LENGTH_SHORT).show();
        }
    }

}
