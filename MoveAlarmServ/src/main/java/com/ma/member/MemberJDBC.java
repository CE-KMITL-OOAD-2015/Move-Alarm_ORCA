package com.ma.member;

import com.ma.JDBC;
import java.sql.*;

/**
 * Created by Admin on 10/15/2015.
 */
public class MemberJDBC implements MemberDbDriver {

    JDBC jdbc;

    public MemberJDBC(){
        jdbc = JDBC.getInstance();
    }

    public boolean getMemberData(Member member){
        Boolean found = false;
        try {
            Statement stmt = jdbc.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Member WHERE Id = " + member.getPk());
            System.out.println(rs);
            if(rs.next()) {
                member.setFirstname(rs.getString("First name"));
                member.setLastname(rs.getString("Last name"));
                member.setGender(rs.getString("Gender"));
                member.setPk(rs.getInt("Id"));
                member.setIdFb(rs.getLong("idFacebook"));
                member.setBirthday(rs.getDate("Birthday"));
                member.setEmail(rs.getString("Email"));
                member.setScore(rs.getInt("Score"));
                member.setPicURL(rs.getString("PicURL"));
                member.setStatus(rs.getString("Status"));
                found = true;
            }else
                System.out.println("no data");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return found;
    }

    public int getPk(long idFB){
        int i = -1;
        try{
            PreparedStatement pstmt = jdbc.getConnection().prepareStatement("SELECT * FROM `Member` WHERE idFacebook = ?");
            pstmt.setLong(1,idFB);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                i = rs.getInt("Id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    public int insertMember(Member member){
        try {
            String sql = "INSERT INTO `Member` " +
                    "(`First name`,`Last name`,`Gender`,`Email`,`Status`,`idFacebook`,`Score`,`Birthday`,`Age`,`PicURL`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = jdbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, member.getFirstname());
            pstmt.setString(2,member.getLastname());
            pstmt.setString(3,member.getGender());
            pstmt.setString(4,member.getEmail());
            pstmt.setString(5,"");
            pstmt.setLong(6,member.getIdFb());
            pstmt.setInt(7,0);
            pstmt.setDate(8,member.getBirthday());
            pstmt.setInt(9,member.getAge());
            pstmt.setString(10,member.getPicURL());
            System.out.println(pstmt);
            pstmt.executeUpdate();
            System.out.println(String.format("Insert data successfully",member.getPk()));
            return getPk(member.getIdFb());//check is data inserted
        }catch (SQLException e){
            e.printStackTrace();
            return -1;// pk = -1 mean sql error
        }
    }

    public int updateMember(Member member){
        try {
            String sql = "UPDATE `Member` "+
            "SET `First name`=?,`Last name`=?,`Gender`=?,`Birthday`=?,`Age`=?,`Email`=?,`PicURL`=? "+
                    "WHERE Id = "+member.getPk();

            PreparedStatement pstmt = jdbc.getConnection().prepareStatement(sql);
            pstmt.setString(1, member.getFirstname());
            pstmt.setString(2,member.getLastname());
            pstmt.setString(3,member.getGender());
            pstmt.setDate(4,member.getBirthday());
            pstmt.setInt(5,member.getAge());
            pstmt.setString(6,member.getEmail());
            pstmt.setString(7,member.getPicURL());
            System.out.println(pstmt);
            pstmt.executeUpdate();
            System.out.println(String.format("ID %d Update successfully",member.getPk()));
            return getPk(member.getIdFb());//check is update
        }catch (SQLException e){
            e.printStackTrace();
            return -1;// pk = -1 mean sql error
        }
    }

    public boolean updatePoint(String userID , int point){
        try{
            String sql = "UPDATE `Member` "+
                    "SET Score = ? WHERE Id = "+ userID;
            PreparedStatement pstmt = jdbc.getConnection().prepareStatement(sql);
            pstmt.setInt(1,point);
            pstmt.executeUpdate();
            System.out.println("Update Point Successfully");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(int userID , String status){
        try{
            String sql = "UPDATE `Member` "+
                    "SET `Status` = ? WHERE Id = "+ userID;
            PreparedStatement pstmt = jdbc.getConnection().prepareStatement(sql);
            pstmt.setString(1,status);
            pstmt.executeUpdate();
            System.out.println("Update Point Successfully");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
