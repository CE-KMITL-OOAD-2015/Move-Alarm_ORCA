package com.ma.event;

import java.util.Date;

/**
 * com.ma.event
 * Last modified by Admin on 11/17/2015.
 * 11:23 PM
 */
public class Event {
    private int id;
    private String title,description,picURL;
    private Date startDate,endDate;

    public Event(){
        this(0,"test","1234","url",new Date(0),new Date());
    }

    public Event(int id,String title,String description,String picURL,Date start,Date end){
        this.id = id;
        this.title = title;
        this.description = description;
        this.picURL = picURL;
        startDate = start;
        endDate = end;
    }

    public int getId() {
        return id;
    }

    public String getPicURL() {
        return picURL;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("ID %d %s start %s : end %s"
                ,id,title,startDate.toString(),endDate.toString());
    }
}
