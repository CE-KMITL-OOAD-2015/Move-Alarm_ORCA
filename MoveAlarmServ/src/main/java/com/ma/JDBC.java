package com.ma;

import java.sql.*;

/**
 * Created by Admin on 10/15/2015.
 */
public class JDBC {

    private Connection connection;

    public JDBC(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/member"+
                "?user=root&password=admin1234");
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

    public Member getMemberData(String id){
        Member temp = null;
        try {
            ResultSet rs = sql("SELECT * FROM Member WHERE idFacebook = " + id);
            System.out.println(rs);
            if(rs.next()) {
                temp = new Member(rs.getString("Frist name"),
                        rs.getString("Last name"),
                        rs.getString("Gender"),
                        rs.getLong("idFacebook"));
                temp.setAge(rs.getInt("Age"));
                temp.setBirthday(rs.getDate("Birthday"));
                temp.setEmail(rs.getString("Email"));
                temp.setScore(rs.getInt("Score"));
                temp.setStatus(rs.getString("Status"));
            }else
                System.out.println("no data");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return temp;
    }

    public boolean insertMember(Member member){
        try {
            String sql = "INSERT INTO `Member` " +
                    "(`Frist name`,`Last name`,`Gender`,`Email`,`Status`,`idFacebook`,`Score`,`Birthday`,`Age`) " +
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
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMember(Member member){
        try {
            String sql = "UPDATE `Member` "+
            "SET `Frist name`=?,`Last name`=?,`Gender`=?,`Score`=?,`Birthday`=?,`Age`=?,`Email`=?,`Status`=?"+
                    "WHERE idFacebook = "+member.getIdFb();

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, member.getFirstname());
            pstmt.setString(2,member.getLastname());
            pstmt.setString(3,member.getGender());
            pstmt.setInt(4,member.getScore());
            pstmt.setDate(5,member.getBirthday());
            pstmt.setInt(6,member.getAge());
            pstmt.setString(7,member.getEmail());
            pstmt.setString(8,member.getStatus());
            pstmt.executeUpdate();
            System.out.println("Update successfully");
            return true;
        }catch (SQLException e){
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
