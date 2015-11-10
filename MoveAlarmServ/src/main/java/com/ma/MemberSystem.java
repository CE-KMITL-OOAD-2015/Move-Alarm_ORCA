package com.ma;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        Member member = null;
            try {
                ResultSet rs = jdbc.sql("SELECT * FROM user WHERE id_fb = " + userID);
                System.out.println(rs);
                rs.next();
                member = new Member(rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                //member.setAge(rs.getInt("Age"));
                member.setBirthday(rs.getDate("birth_day"));
                //member.setEmail(rs.getString(8));
                //member.setScore(rs.getInt(4));
                //member.setStatus(rs.getString(9));
                return member;

            }catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        return member;
    }

}
