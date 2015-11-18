package com.ma.event;



import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ma.event
 * Last modified by Admin on 11/17/2015.
 * 9:46 PM
 */
@RestController
public class EventController {

    EventDbDriver eventDbDriver = new EventJDBC();

    @RequestMapping("/getEvent")
    public String getEvent(){
        Map<String,List<Event>> event = new HashMap<>(2);
        List<Event> arrivingEvent = eventDbDriver.getTodayEvent();
        List<Event> upcomingEvent = eventDbDriver.getUpcomingEvent();
        event.put("today",arrivingEvent);
        event.put("upcoming",upcomingEvent);
        return (new Gson()).toJson(event) ;
    }

}
