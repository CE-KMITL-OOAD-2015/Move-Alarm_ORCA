package com.example.rxusagi.myapplication.model;

/**
 * Created by RXUsagi on 15/10/2015.
 */
import android.content.SharedPreferences;
import android.util.Log;

public class Setting {
    private int sound;
    private boolean vibration;
    private SharedPreferences sharedPreferences;
    public Setting(SharedPreferences sh){
        Log.i("TAG2", "SettingCre");
        sharedPreferences = sh;
        sound = sharedPreferences.getInt("SOUND",50);
        vibration = sharedPreferences.getBoolean("VIBRATE",false);
    }

    public void setSound(int sound) {
        if(sound >= 0 && sound <= 100) {
            this.sound = sound;
        }
    }

    public void setVibration(boolean vibration) {
        this.vibration = vibration;
    }

    public int getSound() {
        return sound;
    }

    public boolean getVibration(){
        return vibration;
    }

    public void update(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("SOUND",sound);
        editor.putBoolean("VIBRATE",vibration);
        editor.commit();
    }
}
