package com.example.rxusagi.myapplication.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.rxusagi.myapplication.Accept_Reject;
import com.example.rxusagi.myapplication.MainActivity;
import com.example.rxusagi.myapplication.R;
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
            Intent intentone = new Intent(context.getApplicationContext(), Accept_Reject.class);
            intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentone);
            //((Activity)context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
        mainActivity.wake();
        //Intent service = new Intent(context,ReceiverInstruction.class);
        //startWakefulService(context, service);
    }
}
