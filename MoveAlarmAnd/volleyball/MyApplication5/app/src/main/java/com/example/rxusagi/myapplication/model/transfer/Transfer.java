package com.example.rxusagi.myapplication.model.transfer;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rxusagi.myapplication.EditProfile;
import com.example.rxusagi.myapplication.FriendActivity;
import com.example.rxusagi.myapplication.LoginActivity;
import com.example.rxusagi.myapplication.MainActivity;
import com.example.rxusagi.myapplication.ProfileActivity;
import com.example.rxusagi.myapplication.model.User_Friend.FriendManagement;
import com.example.rxusagi.myapplication.model.User_Friend.User;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RXUsagi on 04/11/2015.
 */
public class Transfer {
    private static ConnectivityManager connectivityManager;
    private static boolean connected;
    MainActivity mainActivity;
    ImageView thisfake;
    ProgressBar thisprogressBar;
    FriendActivity thisfriendActivity;
    ImageView thisImageview;

    public Transfer(){
        mainActivity = MainActivity.instance();
    }

    public void updatestatus(){
        User user = User.instance();
        if(user.getPrimaryKey()!=-1){
            String url = "http://203.151.92.171:8080/updateStatus";
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            HashMap<String,String> params = new HashMap<String,String>();
            params.put("pk",user.getPrimaryKey()+"");
            params.put("status", user.getStatus());
            JsonObjectRequest req = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    EditProfile editProfile = EditProfile.instance();
                    editProfile.finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("Error: ", error.getMessage());
                }
            });
            requestQueue.add(req);
        }
    }

    public void UpdateScore(int type){
        User user = User.instance();
        if(user.getPrimaryKey()!=-1) {
            String url = "http://203.151.92.171:8080/addPoint?exID=" + type + "&userID=" + user.getPrimaryKey();
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String score = response.optString("newScore").toString();
                    User user = User.instance();
                    user.setScore(Integer.parseInt(score));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("TAGE", "JSONERROR");
                }
            });
            requestQueue.add(req);
        }
    }

    public void getUserInfo(){
        User user = User.instance();
        if(user.getPrimaryKey()!=-1){
            String url = "http://203.151.92.171:8080/getMember?userID="+user.getPrimaryKey();
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    User user = User.instance();
                    if(user.getPrimaryKey() == -1) {
                        user.setPrimaryKey(Integer.parseInt(response.optString("pk").toString()));
                    }
                    user.setName(response.optString("firstname").toString());
                    user.setSurname(response.optString("lastname").toString());
                    user.setGender(response.optString("gender").toString());
                    user.setEmail(response.optString("email").toString());
                    user.setStatus(response.optString("status").toString());
                    user.setScore(Integer.parseInt(response.optString("score").toString()));
                    user.setBirth(response.optString("birthday").toString());
                    user.setAge(Integer.parseInt(response.optString("age").toString()));
                    user.setUrl(response.optString("picURL").toString());
                    Log.i("user", "" + user.getAge() + user.getScore());
                    Log.i("user",""+Integer.parseInt(response.optString("age").toString())+Integer.parseInt(response.optString("score").toString()));
                    ProfileActivity profileActivity = ProfileActivity.instance();
                    if(profileActivity != null) {
                        profileActivity.updateInfo();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("TAGE", "JSONERROR");
                }
            });
            requestQueue.add(req);
        }
    }

    public void getUserInfo2(FriendActivity friendActivity2){
        User user = User.instance();
        if(user.getPrimaryKey()!=-1){
            friendActivity2.waitDialog();
            String url = "http://203.151.92.171:8080/getMember?userID="+user.getPrimaryKey();
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    User user = User.instance();
                    if(user.getPrimaryKey() == -1) {
                        user.setPrimaryKey(Integer.parseInt(response.optString("pk").toString()));
                    }
                    user.setName(response.optString("firstname").toString());
                    user.setSurname(response.optString("lastname").toString());
                    user.setGender(response.optString("gender").toString());
                    user.setEmail(response.optString("email").toString());
                    user.setStatus(response.optString("status").toString());
                    user.setScore(Integer.parseInt(response.optString("score").toString()));
                    user.setBirth(response.optString("birthday").toString());
                    user.setAge(Integer.parseInt(response.optString("age").toString()));
                    user.setUrl(response.optString("picURL").toString());
                    Log.i("user", "" + user.getAge() + user.getScore());
                    Log.i("user", "" + Integer.parseInt(response.optString("age").toString()) + Integer.parseInt(response.optString("score").toString()));
                    findFriendProfile(FriendActivity.instance());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("TAGE", "JSONERROR");
                }
            });
            requestQueue.add(req);
        }
    }

    public void regUser(String firstname,String lastname,String gender,String email,String status,String idFb,String score,String birthday,String age,String pic){
        String url = "http://203.151.92.171:8080/regMember";
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("firstname",firstname);
        params.put("lastname",lastname);
        params.put("gender",gender);
        params.put("email",email);
        params.put("status",status);
        params.put("idFb",idFb);
        params.put("score",score);
        params.put("birthday", birthday);
        params.put("age", age);
        params.put("picURL", pic);
        Log.i("PIC",pic);
        JsonObjectRequest req = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("TAGJSON","1");
                String pk = response.optString("pk").toString();
                Log.i("TAGJSON","2");
                String status = response.optString("status").toString();
                Log.i("TAGJSON", pk + " " + status + "FriendSize " + FriendManagement.getFriendFBID().size());
                User user = User.instance();
                user.setPrimaryKey(Integer.parseInt(pk));
                LoginActivity.instance().finish();
                if(ProfileActivity.instance()!=null){
                    ProfileActivity.instance().update();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error: ", error.getMessage());
            }
        });
        requestQueue.add(req);
    }

    public void testUpdateScore(){
        String url = "http://203.151.92.171:8080/addPoint?exID="+5+"&userID="+1;
        //String url = "http://203.151.92.171:8080/addPoint?exID=5&userID=1";
        Log.i("TAGJSON", "1");
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        Log.i("TAGJSON", "1");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("TAGJSON","1");
                String pk = response.optString("userID").toString();
                Log.i("TAGJSON", "2");
                String score = response.optString("newScore").toString();
                Log.i("TAGJSON", pk + " " + score);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAGE","JSONERROR");
            }
        });
        requestQueue.add(req);
    }

    public void findFriendProfile(final FriendActivity friendAct){
        User user = User.instance();
        thisfriendActivity = friendAct;
        thisfriendActivity.waitDialog();
        if(user.getPrimaryKey()!=-1) {
            String url = "http://203.151.92.171:8080/getFriendList";
            String[] friend = new String[UserManagement.friendManagement.friendFBID.size()];
            int indexString = 0;
            Log.i("testString","abc");
            while(indexString < UserManagement.friendManagement.friendFBID.size()){
                friend[indexString] = UserManagement.friendManagement.friendFBID.get(indexString);
                Log.i("testString",friend[indexString]);
                indexString++;
            }
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            try {
                JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, url, new JSONArray(friend), new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int index = 0;
                        UserManagement.friendManagement.clearfriendList();
                        while (index < response.length()) {
                            try {
                                JSONObject friendResponse = response.getJSONObject(index);
                                Log.i("TESTFRIEND", friendResponse.toString());
                                FriendManagement.instance().addFriend(
                                        friendResponse.optString("idFb").toString(),
                                        friendResponse.optString("firstname").toString(),
                                        friendResponse.optString("lastname").toString(),
                                        friendResponse.optString("gender").toString(),
                                        friendResponse.optString("score").toString(),
                                        friendResponse.optString("age").toString(),
                                        friendResponse.optString("status").toString(),
                                        friendResponse.optString("picURL").toString(),
                                        friendResponse.optString("pk").toString());
                                index++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //thisfriendActivity.cancelDialog();
                        thisfriendActivity.setListView();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(req);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadImage(String url, final ImageView imageView){
        thisImageview = imageView;
        Log.i("Image Url", url);
        if(User.instance().getPrimaryKey() != -1) {
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    thisImageview.setImageBitmap(getCircularBitmap(response));
                }
            }, 0, 0, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(imageRequest);
        }
    }


    public void loadImage(String url, final ImageView imageView, final Dialog wait){
        thisImageview = imageView;
        Log.i("picurl", url + "");
        if(User.instance().getPrimaryKey() != -1) {
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    thisImageview.setImageBitmap(getCircularBitmap(response));
                    if(wait!=null) {
                        wait.cancel();
                    }
                }
            }, 0, 0, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(imageRequest);
        }
    }

    public void test3(){
        String url = "http://203.151.92.171:8080/getFriendList";
        String a[] = {"100001248891144",
                "10204077516090803",
                "100001064013365",
                "10000106401336"};
        try {
            JSONArray b = new JSONArray(a);
            RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, url, b, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    int index = 0;
                    while(index < response.length()) {
                        try {
                            Log.i("testnetwork2",response.toString());
                            Log.i("testnetwork", response.getString(index));
                            JSONObject test3 = response.getJSONObject(index);
                            Log.i("testProfile"+index,test3.optString("pk").toString());
                            Log.i("testProfile"+index,test3.optString("firstname").toString());
                            Log.i("testProfile"+index,test3.optString("lastname").toString());
                            Log.i("testProfile"+index,test3.optString("gender").toString());
                            Log.i("testProfile"+index,test3.optString("email").toString());
                            Log.i("testProfile"+index,test3.optString("status").toString());
                            Log.i("testProfile"+index,test3.optString("idFb").toString());
                            Log.i("testProfile"+index,test3.optString("score").toString());
                            Log.i("testProfile"+index,test3.optString("birthday").toString());
                            Log.i("testProfile"+index,test3.optString("age").toString());
                            index++;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            requestQueue.add(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void test2(){
        String url = "http://api.androidhive.info/volley/person_array.json";
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int indexArray = 0;
                JSONObject jsonObject;
                JSONObject phone;
                while(indexArray < response.length()){
                    try {
                        jsonObject = response.getJSONObject(indexArray);
                        phone = jsonObject.getJSONObject("phone");
                        Log.i("transfer",jsonObject.optString("name").toString()+" "+
                                    jsonObject.optString("email").toString()+" " +
                                    phone.optString("home").toString()+" "+
                                    phone.optString("mobile").toString()+" ");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    indexArray++;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
            }
        });
        requestQueue.add(req);
    }
    public void test(){
        String url = "http://203.151.92.171:8080/regMember";
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("firstname","Thanit");
        params.put("lastname","Wongmasa");
        params.put("gender","Male");
        params.put("email","onionsoul@windowslive.com");
        params.put("status","RXUsagi");
        params.put("idFb","12345");
        params.put("score","0");
        params.put("birthday","1995-06-09");
        params.put("age","20");
        JsonObjectRequest req = new JsonObjectRequest(url,new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("TAGJSON","1");
                String pk = response.optString("pk").toString();
                Log.i("TAGJSON","2");
                String status = response.optString("status").toString();
                Log.i("TAGJSON",pk + " " + status);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error: ", error.getMessage());
            }
        });
        requestQueue.add(req);
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static boolean isOnline(Context context) {
        try {
            connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                    networkInfo.isConnected();
            return connected;
        } catch (Exception e) {
            System.out.println("CheckConnectivity Exception: " + e.getMessage());
            Log.v("connectivity", e.toString());
        }
        return connected;
    }

}
