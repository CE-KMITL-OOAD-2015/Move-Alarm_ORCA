package com.ma.score;

import com.ma.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ma.score
 * Last modified by Admin on 11/19/2015.
 * 1:27 PM
 */
public class ScoreJDBC implements ScoreDriver {

    JDBC jdbc;

    public ScoreJDBC(){
        jdbc = JDBC.getInstance();
    }

    @Override
    public Map<Integer,Integer> getScoreTable() {
        Map<Integer,Integer> table = new HashMap<>();
        try {
            Statement stmt = jdbc.getConnection().createStatement();
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery("SELECT * FROM `exercise` ");
            while (rs.next()) {
                table.put(rs.getInt("exID"), rs.getInt("Score"));
            }
            rs.last();
            System.out.println("exercise "+rs.getRow()+" data");
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return table;
    }
}
