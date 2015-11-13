package com.ma;


import com.google.gson.JsonObject;
import com.ma.model.DbDriver;
import com.ma.model.JDBC;
import com.ma.model.LeaderBoard;
import com.ma.model.Member;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/15/2015.
 * Last modified 10/15/2015.
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
            int pk = 0;
            try{
                pk = Integer.parseInt(userID);
            }catch (Exception e){//catch format
                System.err.println(e);
            }
            Member member = new Member();
            member.setPk(pk);
            Boolean found = jdbc.getMemberData(member);
            return (found) ? member : null;
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

        @RequestMapping(value = "/getFriendList", method = RequestMethod.POST)
        public List<Member> getFriendListID(@RequestBody ArrayList<String> listID){
            ArrayList<Member> list = new ArrayList<>();
            for (String aListID : listID) {
                int pk = jdbc.getPk(Long.parseLong(aListID));
                Member member = getMemberByID(pk + "");
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
            int pk;
            String status;
            if(member != null){
                pk = jdbc.getPk(member.getIdFb());
                member.setPk(pk);
                if(pk == -1)
                    pk = jdbc.insertMember(member);
                else
                    pk = jdbc.updateMember(member);
                status = (pk != -1)?"Success ":"Failed";
            }
            else {
                pk = -2;
                status = "Form not valid";
            }
            jo.addProperty("pk",pk);
            jo.addProperty("status",status);
            System.out.println(jo.toString());//check result in server
            return jo.toString();
        }

        @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
        public String UpdateStatus(@RequestBody(required = false) Member member){
            JsonObject jo = new JsonObject();
            int pk;
            String result;
            if(member != null) {
                pk = member.getPk();
                String status = member.getStatus();
                member = getMemberByID(pk + "");
                if (member != null) {
                    member.setStatus(status);
                    jdbc.updateMember(member);
                }
                result = "Form OK";
            }else{
                pk = -2;
                result = "Form not valid";
            }
            jo.addProperty("pk",pk);
            jo.addProperty("status",result);
            System.out.println(jo.toString());//check result in server
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
