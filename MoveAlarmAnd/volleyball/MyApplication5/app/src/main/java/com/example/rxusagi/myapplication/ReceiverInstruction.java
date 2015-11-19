package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rxusagi.myapplication.model.AlarmManagement;
import com.example.rxusagi.myapplication.model.Instruction;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

public class ReceiverInstruction extends AppCompatActivity {
    private AlarmManagement alarmManagement;
    private Instruction instruction;
    TextView name;
    TextView description;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_instruction);
        MainActivity mainActivity = MainActivity.instance();
        alarmManagement = mainActivity.alarmManagement;
        String sty;
        if(alarmManagement.alarmState.isExstyle()){
            sty = "stretch";
        }
        else{
            sty = "sprint";
        }
        Log.i("TAGN","null");
        instruction = alarmManagement.alarm.startAlarm(sty);
        Log.i("TAGN","null1");
        if(instruction != null) {
            Log.i("TAGR", instruction.getInstruction_name() + instruction.getImg() + instruction.getInstruction());
        }
        Transfer transfer = new Transfer();
        transfer.updateScore(Integer.parseInt(instruction.getInstruction()));
        Log.i("TAGN","null2");
        name = (TextView)findViewById(R.id.instructionname);
        description = (TextView)findViewById(R.id.description);
        name.setText(instruction.getInstruction_name());
        description.setText(instruction.getInstruction2());
        imageView = (ImageView)findViewById(R.id.insImage);
        int resId = getResources().getIdentifier(instruction.getImg(), "mipmap", getPackageName());
        Log.i("TAGRE"," "+resId);
        imageView.setBackgroundResource(resId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Counter counter = new Counter(10000,1000);
        counter.start();
    }

    @Override
    protected void onPause() {
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onPause();
    }

    public class Counter extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            finish();
        }
    }


}
