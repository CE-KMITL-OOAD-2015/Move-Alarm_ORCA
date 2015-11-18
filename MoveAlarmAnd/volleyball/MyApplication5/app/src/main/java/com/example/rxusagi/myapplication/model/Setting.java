package com.example.rxusagi.myapplication.model;

/**
 * Created by RXUsagi on 15/10/2015.
 */
import android.content.SharedPreferences;
import android.util.Log;

public class Setting {
    private boolean sound;
    private boolean vibration;
    private SharedPreferences sharedPreferences;
    public Setting(SharedPreferences sh){
        Log.i("TAG2", "SettingCre");
        sharedPreferences = sh;
        sound = sharedPreferences.getBoolean("SOUNDONOFF",false);
        Log.i("TAG2", "SettingCre");
        vibration = sharedPreferences.getBoolean("VIBRATE",false);
        Log.i("TAG2", "SettingCre");
    }

    public void setSound(boolean sound) {
            this.sound = sound;
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public boolean getSound() {
        return sound;
    }

    public boolean getVibration(){
        return vibration;
    }

    public void update(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("SOUNDONOFF", sound);
        editor.putBoolean("VIBRATE",vibration);
        editor.commit();
    }
}
