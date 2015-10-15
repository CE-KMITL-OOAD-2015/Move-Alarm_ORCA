package com.ma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Admin on 10/15/2015.
 */
public class JDBC {

    private Connection connection;

    public JDBC(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase"+
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
