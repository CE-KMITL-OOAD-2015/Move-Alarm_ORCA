package com.ma;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 10/15/2015.
 */
public class JDBC {

    private Connection connection;

    public JDBC(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/move_alarm"+
                "?user=root&password=");
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
