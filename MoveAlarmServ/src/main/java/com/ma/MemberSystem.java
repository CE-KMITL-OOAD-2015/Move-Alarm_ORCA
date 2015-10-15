package com.ma;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Admin on 10/15/2015.
 */
@RestController
public class MemberSystem {

    JDBC jdbc = new JDBC();

    public Member getMembemById(String idFacebook){
        return new Member();
    }


}
