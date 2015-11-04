package com.ma;


import com.ma.model.JDBC;
import com.ma.model.LeaderBoard;
import com.ma.model.Member;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        @RequestMapping("/getMember")
        public Member getMemberByID(@RequestParam(value = "userID",required = false)
                                        String userID){
            //handle member
            Member member = jdbc.getMemberData(userID);
            if(member != null)
                return member;
            else
                return null;

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
        public List<Member> getFriendListID(@RequestBody ArrayList<String> listID){
            ArrayList<Member> list = new ArrayList<Member>();
            Iterator<String> it = listID.iterator();
            while(it.hasNext()) {
               list.add(getMemberByID(it.next()));
            }
            LeaderBoard leaderBoard = new LeaderBoard(list);
            return leaderBoard.getLeaderboard();
        }

        @RequestMapping(value = "/regMember",method=RequestMethod.POST)
        public String regMember(@RequestBody  Member member){
            if(member != null){
                int pk = jdbc.getPk(member.getIdFb());
                if(pk == -1)
                    pk = jdbc.insertMember(member);
                else
                    jdbc.updateMember(member);
                return (pk != -1)?"Success "+pk:"Failed";
            }
            else
                return "no data in request body";
        }

        public void increasePoint(int exID ,String id){
            Member member = getMemberByID(id);
            if(member != null){
                ScoreCalculator s = ScoreCalculator.getInstance();
                int newScore = s.addScore(member.getScore(),exID);
                jdbc.updatePoint(id,newScore);
            }else
                System.out.println("not found member");
        }
}
