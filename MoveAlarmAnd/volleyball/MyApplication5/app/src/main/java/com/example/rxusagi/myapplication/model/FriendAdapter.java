package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rxusagi.myapplication.R;
import com.example.rxusagi.myapplication.model.User_Friend.Friend;
import com.example.rxusagi.myapplication.model.User_Friend.FriendManagement;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

import java.util.ArrayList;

/**
 * Created by RXUsagi on 14/11/2015.
 */
public class FriendAdapter extends BaseAdapter{
    String[] data;
    Context context;
    LayoutInflater layoutInflater;

    public FriendAdapter(String[] data,Context context){
        super();
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.friendlist,null);
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView status = (TextView)convertView.findViewById(R.id.status);
        TextView score = (TextView)convertView.findViewById(R.id.score);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.friendImage);
        name.setText(FriendManagement.friendlist.get(position).getName() + " " + FriendManagement.friendlist.get(position).getSurname());
        status.setText(FriendManagement.friendlist.get(position).getStatus());
        score.setText(FriendManagement.friendlist.get(position).getScore() + "");
        Transfer transfer = new Transfer();
        transfer.loadImage(FriendManagement.friendlist.get(position).getUrl(),imageView);
        return convertView;
    }
}
