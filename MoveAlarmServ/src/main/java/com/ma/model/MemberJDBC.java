package com.ma.model;

import java.sql.*;

/**
 * Created by Admin on 10/15/2015.
 */
public class MemberJDBC implements MemberDbDriver {

    private Connection connection;
    //Singerton
    private static volatile MemberJDBC memberJdbc = null;

    public static MemberJDBC getInstance() {
        if (memberJdbc == null) {
            synchronized (MemberJDBC.class) {// must test again -- why? This is called "double-checked locking"
                if (memberJdbc == null) {
                    memberJdbc = new MemberJDBC();
                }
            }
        }
        return memberJdbc;
    }


    public MemberJDBC(){
        String username,password,databaseName,cmdCon;
        try{
            //config here
            username = "root";
            password = "admin1234";
            databaseName = "member";
            //prepare string
            cmdCon = String.format("jdbc:mysql://localhost/%s?useUnicode=true&characterEncoding=UTF-8&"+
                    "user=%s&password=%s",databaseName,username,password);
            connection = DriverManager.getConnection("jdbc:mysql://localhost/member"+
                    "?useUnicode=true&characterEncoding=UTF-8&"+
                    "user=root&password=admin1234");
            System.out.println(cmdCon);
            Class.forName("com.mysql.jdbc.Driver");
            //print status
            if(connection!=null){
                System.out.println("Database Connected");
            }else {
                System.out.println("Database Connection Failed");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public boolean getMemberData(Member member){
        Boolean found = false;
        try {
            Statement stmt = connection.createStatement();
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
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM `Member` WHERE idFacebook = ?");
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
            PreparedStatement pstmt = connection.prepareStatement(sql);
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

            PreparedStatement pstmt = connection.prepareStatement(sql);
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
            PreparedStatement pstmt = connection.prepareStatement(sql);
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
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,status);
            pstmt.executeUpdate();
            System.out.println("Update Point Successfully");
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void close(){
        try {
            if (connection != null) {
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
