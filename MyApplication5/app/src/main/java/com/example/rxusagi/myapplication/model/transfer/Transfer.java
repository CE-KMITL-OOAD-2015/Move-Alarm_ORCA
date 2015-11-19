package com.example.rxusagi.myapplication.model.transfer;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rxusagi.myapplication.LoginActivity;
import com.example.rxusagi.myapplication.MainActivity;
import com.example.rxusagi.myapplication.model.User_Friend.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by RXUsagi on 04/11/2015.
 */
public class Transfer {
    MainActivity mainActivity;
    public Transfer(){
        mainActivity = MainActivity.instance();
        Log.i("TAGE","CREATE");
    }

    public void UpdateScore(int type){
        User user = User.instance();
        String url = "http://203.151.92.171:8080/addPoint?exID="+type+"&userID="+user.getPrimaryKey();
        Log.i("TAGJSON","1");
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        Log.i("TAGJSON","1");
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
                Log.i("TAGE","JSONERROR");
            }
        });
        requestQueue.add(req);
    }

    public void regUser(String firstname,String lastname,String gender,String email,String status,String idFb,String score,String birthday,String age){
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
        params.put("birthday",birthday);
        params.put("age",age);
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

    public void testUpdateScore(){
        String url = "http://203.151.92.171:8080/addPoint?exID="+5+"&userID="+1;
        //String url = "http://203.151.92.171:8080/addPoint?exID=5&userID=1";
        Log.i("TAGJSON","1");
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        Log.i("TAGJSON","1");
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("TAGJSON","1");
                String pk = response.optString("userID").toString();
                Log.i("TAGJSON","2");
                String score = response.optString("newScore").toString();
                Log.i("TAGJSON",pk + " " + score);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAGE","JSONERROR");
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
        /**
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("TAGJSON","1");
                String firstname = response.optString("firstname").toString();
                Log.i("TAGJSON","2");
                String lastname = response.optString("lastname").toString();
                Log.i("TAGJSON","3");
                String gender = response.optString("gender").toString();
                Log.i("TAGJSON","4");
                String email = response.optString("email").toString();
                Log.i("TAGJSON","5");
                String status = response.optString("status").toString();
                Log.i("TAGJSON","6");
                String idFb = response.optString("idFb").toString();
                Log.i("TAGJSON","7");
                String score = response.optString("score").toString();
                Log.i("TAGJSON","8");
                String birthday = response.optString("birthday").toString();
                Log.i("TAGJSON","9");
                String age = response.optString("age").toString();
                Log.i("TAGJSON",firstname + " " + lastname + " " + gender + " " + email + " " + status + " " + idFb + " " + score + " " + birthday + " " + age);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAGE","JSONERROR");
            }
        });
        requestQueue.add(jsonRequest);
         **/
    }
}
