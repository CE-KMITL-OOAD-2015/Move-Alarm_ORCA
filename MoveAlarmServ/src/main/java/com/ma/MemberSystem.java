package com.ma;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

/**
 * Created by Admin on 10/15/2015.
 */
@RestController
public class MemberSystem {

    JDBC jdbc = new JDBC();

    @RequestMapping("/connect")
    public JDBC connect(){
        return jdbc;
    }

    @RequestMapping("/")
    public String test(){
        return "Hello Server";
    }


    @RequestMapping("/getMember")
    public Member getMemberByID(@RequestParam(value = "userID",required = false)
                                    String userID){
        //handle member
        return new Member();
    }

}
