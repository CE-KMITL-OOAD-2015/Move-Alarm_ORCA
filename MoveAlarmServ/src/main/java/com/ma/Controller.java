package com.ma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ma.model.Member;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Admin on 11/4/2015.
 */

@RestController
public class Controller {
    @RequestMapping("/")
    public String test(){
        return "Hello Move Alarm Project Server";
    }

}
