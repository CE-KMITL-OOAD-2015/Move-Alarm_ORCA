package com.example.rxusagi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rxusagi.myapplication.model.User_Friend.User;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

public class EditProfile extends AppCompatActivity {
    User user;
    EditText editText;
    public static EditProfile editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        user = User.instance();
        editText = (EditText)findViewById(R.id.edit_status);
        editText.setText(user.getStatus());
        editProfile = this;
    }

    public static EditProfile instance(){
        return editProfile;
    }

    public void onBackClick(View v){
        finish();
    }

    public void onSubmit(View v){
        if(editText.getText().toString().length()<=20) {
            Transfer transfer = new Transfer();
            user.setStatus(editText.getText().toString());
            Log.i("user", user.getStatus());
            transfer.updatestatus();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"Status cannot be more than 20 letters",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickbackground(View v){

    }
}
