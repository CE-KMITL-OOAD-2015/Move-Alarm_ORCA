package com.ma;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
