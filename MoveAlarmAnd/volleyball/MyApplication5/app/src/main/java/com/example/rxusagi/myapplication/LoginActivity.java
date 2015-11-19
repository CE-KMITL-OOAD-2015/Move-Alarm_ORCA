package com.example.rxusagi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.User_Friend.FriendManagement;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;

public class LoginActivity extends Activity {

    private CallbackManager callbackManager;
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager sCallbackManager;
    public UserManagement usermanagemnent;
    private int process;
    ProgressBar progressBar;
    ImageView guest;
    ImageView fake;
    public static LoginActivity loginActivity;
    private String user_id;
    private SharedPreferences sharedPreferences;

    public static LoginActivity instance(){
        return loginActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("MOVEALARM_PREFERENCE", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        usermanagemnent = UserManagement.instance();
        if(UserManagement.instance().wantLogout){
            LoginManager.getInstance().logOut();
            UserManagement.instance().userlogout();
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        if (FriendManagement.wantmorefriend) {
            FriendManagement.wantmorefriend = false;
            String appLinkUrl, previewImageUrl;

            appLinkUrl = "https://www.google.com/";
            previewImageUrl = "blob:https%3A//drive.google.com/a0c8eb9e-ba93-4ab1-a39d-4e0520e29df0";

            if (AppInviteDialog.canShow()) {
                AppInviteContent content = new AppInviteContent.Builder()
                        .setApplinkUrl(appLinkUrl)
                        .setPreviewImageUrl(previewImageUrl)
                        .build();
                AppInviteDialog.show(this, content);
            }
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        guest = (ImageView)findViewById(R.id.guest);
        fake = (ImageView)findViewById(R.id.fake);
        progressBar.setVisibility(View.GONE);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_birthday, public_profile, email, user_friends, read_custom_friendlists"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                user_id = loginResult.getAccessToken().getUserId();
                Log.i("USER ID", user_id);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("facebook_id", user_id);
                editor.commit();
                if (loginResult.getAccessToken() != null) {
                    Set<String> deniedPermissions = loginResult.getRecentlyDeniedPermissions();
                }
                progressBar.setVisibility(View.VISIBLE);
                guest.setClickable(false);
                loginButton.setClickable(false);
                fake.setBackgroundColor(Color.parseColor("#70000000"));
                setUserInfo();
            }
            @Override
            public void onCancel() {
                Log.i("TAG2", "cancel");
                info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("TAG2", "error");
                info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginActivity = this;
    }
    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
        if(AccessToken.getCurrentAccessToken()!=null) {
            user_id = sharedPreferences.getString("facebook_id","");
            if(!user_id.equals("")) {
                Log.i("NET","AUTO");
                setUserInfo();
                finish();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    private void setUserInfo(){
        process = 0;
        GraphRequest friendRequest = new GraphRequest(AccessToken.getCurrentAccessToken(),'/' + user_id + "/friends",null,HttpMethod.GET,new GraphRequest.Callback(){
                    public void onCompleted(GraphResponse response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject();
                            JSONArray array = jsonObject.getJSONArray("data");
                            int i = 0;
                            FriendManagement.instance().clearFBID();
                            while(i < array.length()){
                                JSONObject friend = array.getJSONObject(i);
                                FriendManagement.getFriendFBID().add(friend.getString("id"));
                                i++;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
        );

        GraphRequest userRequest = new GraphRequest(AccessToken.getCurrentAccessToken(),'/' + user_id,null,HttpMethod.GET,new GraphRequest.Callback(){
                    public void onCompleted(GraphResponse response) {
                        JSONObject a = response.getJSONObject();
                        JSONObject b = null;
                        try {
                            b = a.getJSONObject("age_range");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String birthdayJA[] = a.optString("birthday").toString().split("/");
                        try {
                            URL picurl = null;
                            String urlpic = "";
                            if(Profile.getCurrentProfile()!=null){
                                picurl = new URL(Profile.getCurrentProfile().getProfilePictureUri(200, 200).toString());
                                urlpic = picurl.toString();
                            }
                            usermanagemnent.regisUser(a.optString("first_name").toString(), a.optString("last_name").toString(), a.optString("gender").toString(), a.optString("email").toString(), a.optString("id").toString(), birthdayJA[2] + "-" + birthdayJA[1] + "-" + birthdayJA[0], b.optString("min").toString(), urlpic);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }

                }
        );
        Bundle params = new Bundle();
        params.putString("fields", "id");
        friendRequest.setParameters(params);
        friendRequest.executeAsync();
        Bundle parameters = new Bundle();
        parameters.putString("fields","id,gender,birthday,first_name,last_name,age_range");
        userRequest.setParameters(parameters);
        userRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onGuest(View v){
        UserManagement.stateguest = true;
        finish();
    }
}
