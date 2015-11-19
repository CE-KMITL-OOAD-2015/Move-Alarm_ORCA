package com.example.rxusagi.myapplication.model;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by RXUsagi on 19/11/2015.
 */
public class Counter extends CountDownTimer {

    private AppCompatActivity activity;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Counter(long millisInFuture, long countDownInterval,AppCompatActivity activity) {
        super(millisInFuture, countDownInterval);
        this.activity = activity;
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }


    @Override
    public void onFinish() {
        activity.finish();
    }
}