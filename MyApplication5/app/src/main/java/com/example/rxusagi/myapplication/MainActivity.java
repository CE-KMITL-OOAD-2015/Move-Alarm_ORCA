package com.example.rxusagi.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.rxusagi.myapplication.model.AlarmManagement;
import com.example.rxusagi.myapplication.model.InstructionReceiver;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity;
    public AlarmManagement alarmManagement;
    public UserManagement userManagement;
    protected TextView textview;
    private TextView hour10;
    private TextView hour1;
    private TextView hdoubleColon;
    private TextView minute10;
    private TextView minute1;
    private TextView mdoubleColon;
    private TextView second10;
    private TextView second1;
    private CounterClass timer;
    private AlarmManager alarmManager;
    @Override
    protected void onStart() {
        super.onStart();
        mainActivity = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        count();
    }

    public void wake(){
        Intent intent = new Intent(MainActivity.this,InstructionReceiver.class);
        PendingIntent send = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
        Long time = SystemClock.elapsedRealtime();
        time += alarmManagement.getNextTimeMillisec();
        //time += 20*1000;
        Log.i("TAGW","WAKE");
        if(alarmManagement.getNextTimeMillisec() > 10) {
            Log.i("TAGW","WAKE");
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,time,send);
        }
    }

    private void count(){
        if(alarmManagement.getNextTimeMillisec()!=0) {
            timer = new CounterClass(alarmManagement.getNextTimeMillisec(), 1000);
            timer.start();
        }
        wake();
    }
    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }

    public static MainActivity instance(){
        return mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("TAGW", "Create");
        userManagement = LoginActivity.instance().usermanagemnent;
        //userManagement = new UserManagement();
        setContentView(R.layout.activity_main);
        bindWidget();
        setinit();
        try {
            alarmManagement = new AlarmManagement(this,new InputStreamReader(getAssets().open("instruction.CSV"),"UTF-8"),getSharedPreferences("MOVEALARM_PREFERENCE", Context.MODE_PRIVATE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void bindWidget(){
        hour10 = (TextView) findViewById(R.id.hournumber10);
        hour1 = (TextView) findViewById(R.id.hournumber1);
        hdoubleColon = (TextView) findViewById(R.id.hdoublecolon);
        minute10 = (TextView) findViewById(R.id.minutenumber10);
        minute1 = (TextView) findViewById(R.id.minutenumber1);
        mdoubleColon = (TextView) findViewById(R.id.mdoublecolon);
        second10 = (TextView) findViewById(R.id.secondnumber10);
        second1 = (TextView) findViewById(R.id.secondnumber1);
    }

    protected void setinit(){
        hour10.setText("0");
        hour1.setText("0");
        hdoubleColon.setText(":");
        minute10.setText("0");
        minute1.setText("0");
        mdoubleColon.setText(":");
        second10.setText("0");
        second1.setText("0");
    }

    public void onSetAlarmClicked(View v){
        startActivity(new Intent(getApplicationContext(), SetAlarmActivity.class));
    }


    public void onSettingClicked(View v){
        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
    }

    public void setInstruction() {
        startActivity(new Intent(getApplicationContext(), Accept_Reject.class));
        finish();
    }

    public void onAboutClick(View v){
        startActivity(new Intent(getApplicationContext(),AboutActivity.class));
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long mil = millisUntilFinished;
            hour10.setText(""+(TimeUnit.MILLISECONDS.toHours(mil)/10));
            hour1.setText(""+(TimeUnit.MILLISECONDS.toHours(mil)%10));
            minute10.setText(""+(TimeUnit.MILLISECONDS.toMinutes(mil)-TimeUnit.MILLISECONDS.toHours(mil)*60)/10);
            minute1.setText(""+(TimeUnit.MILLISECONDS.toMinutes(mil)-TimeUnit.MILLISECONDS.toHours(mil)*60)%10);
            second10.setText(""+(TimeUnit.MILLISECONDS.toSeconds(mil)-TimeUnit.MILLISECONDS.toMinutes(mil)*60)/10);
            second1.setText(""+(TimeUnit.MILLISECONDS.toSeconds(mil)-TimeUnit.MILLISECONDS.toMinutes(mil)*60)%10);
        }

        @Override
        public void onFinish() {
            hour10.setText("-");
            hour1.setText("-");
            minute10.setText("-");
            minute1.setText("-");
            second10.setText("-");
            second1.setText("-");
            count();
        }
    }

    public void onProfileClick(View v){
        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
    }

    public void onFriendClick(View v){
        Transfer t = new Transfer();
        Log.i("TAGJSON", "JJ");
        t.regUser("PIPE","JJ","Male","email","AA","1431","1234","2011/12/12","12");
        //t.test();
        //t.testUpdateScore();
    }

    @Override
    protected void onDestroy() {
        Log.i("TAGD", "DESTORYMAIN");
        super.onDestroy();
    }
    /**
    protected void Login(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.main_activity);
        //info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_birthday, public_profile, email, user_friends, read_custom_friendlists"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("TAG2", "User ID:  " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
                System.out.println("permission0   :  " + AccessToken.getCurrentAccessToken().getPermissions());
                Log.i("TAG2", "success");
                String user_id = loginResult.getAccessToken().getUserId();

                if (loginResult.getAccessToken() != null) {
                    Log.i("TAG2", "token!= null");
                    Set<String> deniedPermissions = loginResult.getRecentlyDeniedPermissions();
                    Log.i("TAG2", "loginmanager");
                    System.out.println("Access token " + AccessToken.getCurrentAccessToken().getToken());
                    Log.i("TAG2", AccessToken.getCurrentAccessToken().toString());
                    System.out.println("permission1   :  " + AccessToken.getCurrentAccessToken().getPermissions() + "    permission denided    : " + deniedPermissions);

                }

                GraphRequest friendRequest = new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        '/' + user_id + "/friends"
                        ,
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                System.out.println("ffffffffffffffffffffffff" + response.toString());
                                try {
                                    JSONObject jsonObject = response.getJSONObject();
                                    Log.d("data", jsonObject.toString(0));

                                    JSONArray array = jsonObject.getJSONArray("data");
                                    Log.i("array length", "a" + array.length());
                                    System.out.println("permission1   :  " + AccessToken.getCurrentAccessToken().getPermissions());
                                    for (int i = 0; i < array.length(); i++) {

                                        JSONObject friend = array.getJSONObject(i);

                                        Log.d("for id :", friend.getString("id"));
                                        Log.d("for name ", friend.getString("name"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                );
                Bundle params = new Bundle();
                params.putString("fields", "id,name,friends");
                friendRequest.setParameters(params);
                friendRequest.executeAsync();
                //startActivity(new Intent(getApplicationContext(), InviteActivity.class));
            }

            @Override
            public void onCancel() {
                Log.i("TAG2", "cancel");
                //info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {

                //Log.i("TAG2","error");info.setText("Login attempt failed.");
            }
        });
    }
     **/
}
