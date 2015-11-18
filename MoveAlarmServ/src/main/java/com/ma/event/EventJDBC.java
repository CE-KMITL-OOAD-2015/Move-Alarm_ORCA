package com.ma.event;

import com.ma.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * com.ma.event
 * Last modified by Admin on 11/17/2015.
 * 9:47 PM
 */
public class EventJDBC implements EventDbDriver{

    JDBC jdbc;
    public EventJDBC(){
        jdbc = JDBC.getInstance();
    }

    @Override
    public List<Event> getTodayEvent() {
        List<Event> events = new ArrayList<>();
        try {
            Timestamp tstmp = new Timestamp(System.currentTimeMillis());
            PreparedStatement preparedStatement = jdbc.getConnection().prepareStatement("SELECT * FROM `event` WHERE `start` < ? AND `end` > ?");
            preparedStatement.setTimestamp(1,tstmp);
            preparedStatement.setTimestamp(2,tstmp);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Event event = new Event(rs.getInt("eventID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("picture"),
                        rs.getDate("start"),
                        rs.getDate("end"));
                events.add(event);
            }
            rs.last();
            System.out.println(rs.getRow()+" data");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return events;
    }

    @Override
    public List<Event> getUpcomingEvent() {
        List<Event> events = new ArrayList<>();
        try {
            Calendar calendar = Calendar.getInstance();
            Timestamp tstmp = new Timestamp(calendar.getTime().getTime());//currenttime
            calendar.add(Calendar.DATE, 1);
            Timestamp tomorrow = new Timestamp(calendar.getTime().getTime());
            PreparedStatement preparedStatement = jdbc.getConnection().prepareStatement("SELECT * FROM `event` WHERE `start` BETWEEN ?  AND ?");
            preparedStatement.setTimestamp(1,tstmp);
            preparedStatement.setTimestamp(2,tomorrow);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Event event = new Event(rs.getInt("eventID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("picture"),
                        rs.getDate("start"),
                        rs.getDate("end"));
                events.add(event);
            }
            rs.last();
            System.out.println(rs.getRow()+" data");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return events;
    }
}
