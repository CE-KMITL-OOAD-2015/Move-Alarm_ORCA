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

    /*to test request*/
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public String testJson(@RequestBody String str){
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Map<String,List<Map<String,String>>>>(){}.getType();
        Map<String, List<Map<String,String>>> myMap = gson.fromJson(str, collectionType);
        System.out.println(myMap.get("friend").get(1).get("id"));
        return null;
    }

    /**
     * test to check json
     */
    @RequestMapping("/getListTest")
    public List<Member> testFriend(){
        List<Member> list = new ArrayList<>();
        list.add(new Member());
        list.add(new Member());
        list.add(new Member());
        return list;
    }
}
