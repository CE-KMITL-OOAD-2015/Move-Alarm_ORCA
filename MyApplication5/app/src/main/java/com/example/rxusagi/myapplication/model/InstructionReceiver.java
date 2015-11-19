package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.rxusagi.myapplication.MainActivity;
import com.example.rxusagi.myapplication.ReceiverInstruction;

/**
 * Created by RXUsagi on 03/11/2015.
 */
public class InstructionReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAGR", "RECEIVER");
        MainActivity mainActivity = MainActivity.instance();
        if(mainActivity.alarmManagement.alarmState.isActivate()) {
            mainActivity.setInstruction();
        }
        mainActivity.wake();
        //Intent service = new Intent(context,ReceiverInstruction.class);
        //startWakefulService(context, service);
    }
}
