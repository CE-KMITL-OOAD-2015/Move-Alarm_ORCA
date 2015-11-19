package com.example.rxusagi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.User_Friend.User;
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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class LoginActivity extends Activity {

    private CallbackManager callbackManager;
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager sCallbackManager;
    public UserManagement usermanagemnent;
    int i = 0;

    public static LoginActivity loginActivity;
    public static LoginActivity instance(){
        return loginActivity;
    }
    @Override
    protected void onStart() {
        super.onStart();
        loginActivity = this;
    }


    @Override
    protected void onDestroy() {
        Log.i("TAG2", "Destroy");
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        Log.i("TAG2","RESUME");
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        Log.i("TAG2","PAUSE");
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onStop() {
        Log.i("TAG2", "STOP");
        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TAG2", "Create");
        usermanagemnent = new UserManagement();
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        //info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_birthday, public_profile, email, user_friends, read_custom_friendlists"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //info.setText("User ID:  " + loginResult.getAccessToken().getUserId() + "\n" + "Auth Token: " + loginResult.getAccessToken().getToken());
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
                                    //String score = response.optString("newScore").toString();
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
                GraphRequest userRequest = new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        '/' + user_id
                        ,
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                System.out.println("TAGFFF");
                                System.out.println("TAGFFF" + response.toString());
                                JSONObject a = response.getJSONObject();
                                Log.i("TAGFFF", a.optString("id").toString() + a.optString("email").toString() + a.optString("gender").toString() + a.optString("birthday").toString() + a.optString("first_name").toString() + a.optString("last_name").toString());
                                JSONObject b = null;
                                try {
                                    b = a.getJSONObject("age_range");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String birthdayJA[] = a.optString("birthday").toString().split("/");
                                usermanagemnent.setUser(a.optString("first_name").toString(), a.optString("last_name").toString(),
                                        a.optString("gender").toString(), a.optString("email").toString(), a.optString("id").toString(), birthdayJA[2]+"-"+birthdayJA[1]+"-"+birthdayJA[0], b.optString("max").toString());
                                System.out.println("TAGFFF" + b.optString("max").toString());
                            }

                        }
                );
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday,first_name,last_name,age_range");
                userRequest.setParameters(parameters);
                userRequest.executeAsync();
                //Profile profile = Profile.getCurrentProfile();
                //Log.i("TAGREST",profile.getFirstName()+profile.getLastName()+profile.getId());
                /**
                Profile profile = Profile.getCurrentProfile();
                String firstName = profile.getFirstName();
                System.out.println(profile.getProfilePictureUri(20,20));
                System.out.println(profile.getLinkUri());
                Log.i("firstname12 :",firstName);
               GraphRequest request = GraphRequest.newMeRequest(
                       loginResult.getAccessToken(),
                       new GraphRequest.GraphJSONObjectCallback() {
                           @Override
                           public void onCompleted(
                                   JSONObject object,
                                   GraphResponse response) {
                               Log.i("TAGRESPOND", "RES");
                               Log.i("TAGRESPOND", response.toString());
                               Log.i("TAGRES", response.getJSONObject().optString("first_name").toString());
                               Log.i("TAGRES",response.getJSONObject().optString("last_name").toString());
                               Log.i("TAGRES",response.getJSONObject().optString("gender").toString());
                               Log.i("TAGRES",response.getJSONObject().optString("birthday").toString());
                               Log.i("TAGRES",response.getJSONObject().optString("email").toString());
                               // Applica
                               Log.v("LoginActivity", response.toString());
                           }
                       });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
                **/

                Bundle params = new Bundle();
                params.putString("fields", "id,name,friends");
                friendRequest.setParameters(params);
                friendRequest.executeAsync();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
