package com.ma.model;

/**
 * Created by Admin on 11/7/2015.
 */
public interface DbDriver {

    boolean getMemberData(Member member);

    int getPk(long idFB);

    int insertMember(Member member);

    int updateMember(Member member);

    boolean updatePoint(String userID , int point);

}
