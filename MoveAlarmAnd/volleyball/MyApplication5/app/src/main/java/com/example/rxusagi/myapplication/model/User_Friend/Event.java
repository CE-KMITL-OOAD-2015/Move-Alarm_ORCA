package com.example.rxusagi.myapplication.model.User_Friend;

import android.util.Log;

/**
 * Created by RXUsagi on 18/11/2015.
 */
public class Event {
    private String type;
    private String id;
    private String title;
    private String description;
    private String picURL;
    private String startDate;
    private String endDate;
    public Event(String type,String id,String title,String description,String picURL,String startDate,String endDate){
        this.type = type;
        this.id = id;
        this.title = title;
        this.description = description;
        this.picURL = picURL;
        this.startDate = startDate;
        this.endDate = endDate;
        Log.i("Event Cre",type + " " + id + " " + title + " " + description + " " + picURL + " " + startDate + " " + endDate);
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getId() {
        return id;
    }

    public String getPicURL() {
        return picURL;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
