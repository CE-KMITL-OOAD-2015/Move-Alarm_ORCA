package com.example.rxusagi.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.EventAdapter;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

public class EventActivity extends AppCompatActivity {
    ListView listView;
    Dialog wait;
    Dialog noconnection;
    public static EventActivity eventActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        listView = (ListView)findViewById(R.id.listview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        eventActivity = this;
        if (Transfer.isOnline(getApplicationContext())){
            wait = new Dialog(EventActivity.this);
            wait.requestWindowFeature(Window.FEATURE_NO_TITLE);
            wait.setContentView(R.layout.waitdialog);
            wait.setCancelable(false);
            wait.show();
            Transfer transfer = new Transfer();
            transfer.getEvent();
        }else{
            noconnection = new Dialog(EventActivity.this);
            noconnection.requestWindowFeature(Window.FEATURE_NO_TITLE);
            noconnection.setContentView(R.layout.connectdialog);
            noconnection.setCancelable(false);
            RelativeLayout relativeLayout = (RelativeLayout) noconnection.findViewById(R.id.okDialog);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            });
            noconnection.show();
        }
    }

    public void cancelWait(){
        if(wait != null) {
            wait.cancel();
            setListView();
        }
    }
    public static EventActivity instance(){
        return  eventActivity;
    }

    private void setListView(){
        listView.setAdapter(new EventAdapter(new String[UserManagement.eventArrayList.size()], EventActivity.this));
        listView.setClickable(false);
    }

    public void onBackClick(View v){
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
