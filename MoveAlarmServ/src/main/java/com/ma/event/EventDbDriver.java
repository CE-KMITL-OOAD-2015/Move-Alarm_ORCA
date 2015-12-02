package com.ma.event;

import java.util.List;

/**
 * com.ma.event
 * Last modified by Admin on 11/17/2015.
 * 9:46 PM
 */
public interface EventDbDriver {

    public List<Event> getTodayEvent();

    public List<Event> getUpcomingEvent();

}
