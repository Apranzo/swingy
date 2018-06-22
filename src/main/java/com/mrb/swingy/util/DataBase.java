package com.mrb.swingy.util;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by chvs on 22.06.2018.
 */
public class DataBase {
    public static final String DATA_BASE_URL = "jdbc:sqlite:heroes.db";
    private static Connection connection;

    public static void connect(){
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DATA_BASE_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        connection = conn;
    }

    public static void close(){
        try {
            if (connection != null)
                connection.close();
            connection = null;
            System.out.println("Connection to SQLite has been closed.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){
        if (connection == null)
            connect();
        return connection;
    }

    public static void insert(String name, String className, int level, int xp, int attack, int defense, int hp){
        String sqlQuery = "INSERT INTO heroes(name, class, level, xp, attack, defense, hp) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)){
            pstmt.setString(1, name);
            pstmt.setString(2, className);
            pstmt.setInt(3, level);
            pstmt.setInt(4, xp);
            pstmt.setInt(5, attack);
            pstmt.setInt(6, defense);
            pstmt.setInt(7, hp);
            pstmt.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> selectAll(){
        String sqlQuery = "SELECT * FROM heroes";
        ArrayList<String> arrayList = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)){
            for (int i = 1; rs.next(); i++){
                arrayList.add(String.format("%d. %s (%s)", i, rs.getString("name"), rs.getString("class")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }

    public static ArrayList<String> selectById(int id){
        String sqlQuery = "SELECT * FROM heroes WHERE id=?";
        ArrayList<String> arrayList = new ArrayList<>();

        try (PreparedStatement pstmt = getConnection().prepareStatement(sqlQuery)){

            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                arrayList.add(rs.getString("name"));
                arrayList.add(rs.getString("class"));
                arrayList.add(Integer.toString(rs.getInt("level")));
                arrayList.add(Integer.toString(rs.getInt("xp")));
                arrayList.add(Integer.toString(rs.getInt("attack")));
                arrayList.add(Integer.toString(rs.getInt("defense")));
                arrayList.add(Integer.toString(rs.getInt("hp")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return arrayList;
    }
}
