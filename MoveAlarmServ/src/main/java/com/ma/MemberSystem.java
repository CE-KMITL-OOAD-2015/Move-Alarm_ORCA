package com.ma;


import com.google.gson.JsonObject;

import com.ma.model.DbDriver;
import com.ma.model.JDBC;
import com.ma.model.LeaderBoard;
import com.ma.model.Member;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 10/15/2015.
 */
@RestController
public class MemberSystem {

        DbDriver jdbc = JDBC.getInstance();

        @RequestMapping("/connect")
        public DbDriver connect(){
            return jdbc;
        }

        @RequestMapping("/getMember")
        public Member getMemberByID(@RequestParam(value = "userID",required = false)
                                        String userID){
            Member member = new Member();
            member.setPk(Integer.parseInt(userID));
            Boolean found = jdbc.getMemberData(member);
            if(member != null)
                return member;
            else
                return null;
        }

        /**
         * test to check json
         */
        @RequestMapping("/getFriendListTest")
        public List<Member> testFriend(){
            List<Member> list = new ArrayList<Member>();
            list.add(new Member());
            list.add(new Member());
            list.add(new Member());
            return list;
        }

        @RequestMapping(value = "/getFriendList", method = RequestMethod.POST)
        public List<Member> getFriendListID(@RequestBody ArrayList<String> listID){
            ArrayList<Member> list = new ArrayList<Member>();
            Iterator<String> it = listID.iterator();
            while(it.hasNext()) {
                int pk = jdbc.getPk(Long.parseLong(it.next()));
                Member member = getMemberByID(pk+"");
                System.out.println(member);
                list.add(member);
            }
            if(!list.isEmpty()) {
                LeaderBoard leaderBoard = new LeaderBoard(list);
                return leaderBoard.getLeaderboard();
            }else
                return null;
        }

        @RequestMapping(value = "/regMember",method=RequestMethod.POST)
        public String regMember(@RequestBody(required = false) Member member){
            JsonObject jo = new JsonObject();
            if(member != null){
                int pk = jdbc.getPk(member.getIdFb());
                member.setPk(pk);
                if(pk == -1)
                    pk = jdbc.insertMember(member);
                else
                    pk = jdbc.updateMember(member);
                String s = (pk != -1)?"Success ":"Failed";
                jo.addProperty("pk",pk);
                jo.addProperty("status",s);
                System.out.println(jo.toString());
            }
            else {
                jo.addProperty("pk",-2);
                jo.addProperty("status","Form not valid");
            }
            return jo.toString();
        }

        @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
        public String UpdateStatus(@RequestBody(required = false) Member member){
            JsonObject jo = new JsonObject();
            if(member != null) {
                int pk = member.getPk();
                String status = member.getStatus();
                member = getMemberByID(pk + "");
                if (member != null) {
                    member.setStatus(status);
                    jdbc.updateMember(member);
                }
                jo.addProperty("pk",pk);
                jo.addProperty("status","200 OK");
            }else{
                jo.addProperty("pk",-2);
                jo.addProperty("status","Form not valid");
            }
            return jo.toString();
        }

        @RequestMapping("/addPoint")
        public String increasePoint(@RequestParam(value = "exID",defaultValue = "0") int exID ,
                                  @RequestParam(value = "userID") String id){
            Member member = getMemberByID(id);
            if(member != null){
                ScoreCalculator s = ScoreCalculator.getInstance();
                int newScore = s.addScore(member.getScore(),exID);
                jdbc.updatePoint(id,newScore);
                JsonObject json = new JsonObject();
                json.addProperty("userID",id);
                json.addProperty("newScore",newScore);
                return json.toString();
            }else {
                System.out.println("not found member");
                return (new JsonObject()).toString();
            }
        }
}
