package com.example.rxusagi.myapplication.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rxusagi.myapplication.R;
import com.example.rxusagi.myapplication.model.User_Friend.Event;
import com.example.rxusagi.myapplication.model.User_Friend.FriendManagement;
import com.example.rxusagi.myapplication.model.User_Friend.UserManagement;
import com.example.rxusagi.myapplication.model.transfer.Transfer;

/**
 * Created by RXUsagi on 18/11/2015.
 */
public class EventAdapter extends BaseAdapter {
    String[] data;
    Context context;
    LayoutInflater layoutInflater;

    public EventAdapter(String[] data,Context context){
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
        convertView = layoutInflater.inflate(R.layout.eventlist,null);
        TextView uptoday = (TextView)convertView.findViewById(R.id.uptoday);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        TextView header = (TextView)convertView.findViewById(R.id.header);
        TextView des = (TextView)convertView.findViewById(R.id.description);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        Event event = UserManagement.eventArrayList.get(position);
        uptoday.setText(event.getType());
        date.setText(event.getStartDate() + " - " + event.getEndDate());
        header.setText(event.getTitle());
        des.setText(event.getDescription());
        Transfer transfer = new Transfer();
        transfer.loadImagenocrop(event.getPicURL(),imageView);
        convertView.setClickable(false);
        return convertView;
    }
}
