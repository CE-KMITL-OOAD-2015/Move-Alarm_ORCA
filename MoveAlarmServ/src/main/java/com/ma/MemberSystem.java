package com.ma;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

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
            return "Hello Move Alarm Project Server";
        }


        @RequestMapping("/getMember")
        public Object getMemberByID(@RequestParam(value = "userID",required = false)
                                        String userID){
            //handle member
            Member member = jdbc.getMemberData(userID);
            if(member != null)
                return member;
            else
                return "not found member";

        }

        /**
         * test to check json
         */
        @RequestMapping("/getFriendListTest")
        public ArrayList<Member> testFriend(){
            ArrayList<Member> list = new ArrayList<Member>();
            list.add(new Member());
            list.add(new Member());
            list.add(new Member());
            return list;
        }

        @RequestMapping(value = "/getFriendList", method = RequestMethod.POST)
        public ArrayList<Object> getFriendListID(@RequestBody ArrayList<String> listID){
            ArrayList<Object> list = new ArrayList<>();
            Iterator<String> it = listID.iterator();
            while(it.hasNext()) {
               list.add(getMemberByID(it.next()));
            }
            return list;
        }

        @RequestMapping(value = "/regMember",method=RequestMethod.POST)
        public String regMember(@RequestBody  Member member){
            if(member != null){
                boolean complete;
                Object temp = getMemberByID(member.getIdFb() + "");
                if(temp.getClass().equals(String.class))
                    complete = jdbc.insertMember(member);
                else
                    complete = jdbc.updateMember(member);
                return (complete)?"Success":"Failed";
            }
            else
                return "no data in request body";
        }


}
