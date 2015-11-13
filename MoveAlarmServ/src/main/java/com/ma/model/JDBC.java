package com.ma.model;

import com.ma.model.Member;

import java.sql.*;

/**
 * Created by Admin on 10/15/2015.
 */
public class JDBC implements DbDriver{

    private Connection connection;
    //Singerton
    private static volatile JDBC jdbc = null;

    public static JDBC getInstance() {
        if (jdbc == null) {
            synchronized (JDBC.class) {// must test again -- why? This is called "double-checked locking"
                if (jdbc == null) {
                    jdbc = new JDBC();
                }
            }
        }
        return jdbc;
    }
    public JDBC(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/member"+
                            "?useUnicode=true&characterEncoding=UTF-8&"+
                "user=root&password=admin1234");
            if(connection!=null){
                System.out.println("Database Connected");
            }else {
                System.out.println("Database Connection Failed");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public ResultSet sql(String cmd){
        ResultSet rs = null;

        try{
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(cmd);
        }catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return rs;
    }

    public boolean getMemberData(Member member){
        Boolean complete = false;
        try {
            ResultSet rs = sql("SELECT * FROM Member WHERE Id = " + member.getPk());
            System.out.println(rs);
            if(rs.next()) {
                member.setFirstname(rs.getString("First name"));
                member.setLastname(rs.getString("Last name"));
                member.setGender(rs.getString("Gender"));
                member.setPk(rs.getInt("Id"));
                member.setIdFb(rs.getLong("idFacebook"));
                member.setAge(rs.getInt("Age"));
                member.setBirthday(rs.getDate("Birthday"));
                member.setEmail(rs.getString("Email"));
                member.setScore(rs.getInt("Score"));
                member.setStatus(rs.getString("Status"));
                complete = true;
            }else
                System.out.println("no data");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return complete;
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
                    "(`First name`,`Last name`,`Gender`,`Email`,`Status`,`idFacebook`,`Score`,`Birthday`,`Age`) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
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
            System.out.println(pstmt);
            pstmt.executeUpdate();
            System.out.println("Insert successfully yay yay");
            return getPk(member.getIdFb());
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public int updateMember(Member member){
        try {
            String sql = "UPDATE `Member` "+
            "SET `First name`=?,`Last name`=?,`Gender`=?,`Birthday`=?,`Age`=?,`Email`=?,`Status`=?"+
                    "WHERE Id = "+member.getPk();

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, member.getFirstname());
            pstmt.setString(2,member.getLastname());
            pstmt.setString(3,member.getGender());
            pstmt.setDate(4,member.getBirthday());
            pstmt.setInt(5,member.getAge());
            pstmt.setString(6,member.getEmail());
            pstmt.setString(7,member.getStatus());
            System.out.println(pstmt);
            pstmt.executeUpdate();
            System.out.println("Update successfully");
            return member.getPk();//not effect to pk
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
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
