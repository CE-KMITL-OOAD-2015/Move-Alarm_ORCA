package com.ma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * com.ma
 * Last modified by Admin on 11/17/2015.
 * 9:49 PM
 */
public class JDBC {
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
        String username,password,databaseName,cmdCon;
        try{
            //config here
            username = "root";
            password = "admin1234";
            databaseName = "member";
            //prepare string
            cmdCon = String.format("jdbc:mysql://localhost/%s?useUnicode=true&characterEncoding=UTF-8&"+
                    "user=%s&password=%s",databaseName,username,password);
            //connect to database
            connection = DriverManager.getConnection("jdbc:mysql://localhost/member" +
                    "?useUnicode=true&characterEncoding=UTF-8&" +
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

    public Connection getConnection() {
        return connection;
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
