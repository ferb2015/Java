package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
@RestController
public class Api2Controller {
    public static final String URL = "jdbc:mysql://localhost:3306/newbase?useUnicode=true&characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PASSWORD = "12345678";

    @RequestMapping("/test")

    public static void aa(@RequestParam("bb") String bb)throws Exception {
    //public static void aa()throws Exception {
            Connection conn = null;
            Statement stmt = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stmt = conn.createStatement();
         String sql = "SELECT Websites.id, " +
                "Websites.name, access_log.count, " +
                "access_log.date FROM access_log INNER JOIN Websites " +
                "ON Websites.id=access_log.site_id";
                //原句很长，每次回车，会自动补上“和+，不用手动补。
        ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                int count  = rs.getInt("count");
                Date date = rs.getDate("date");//注意 类型，一开始写错了，就一直通过不了，写成string了，但其实是date型。
                //String date = rs.getString("date");
                // 输出数据
                System.out.print("id: " + id);
                System.out.print(", name: " + name);
                System.out.print(", count: " + count);
                System.out.print(", date: " + date);
                System.out.print("\n");
            }
        //5. 清理环境
        rs.close();
        stmt.close();
        conn.close();
        }catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
    }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
    }finally{
        //finally block used to close resources
        try{
            if(stmt!=null)
                conn.close();
        }catch(SQLException se){
        }// do nothing
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }//end try
        System.out.println("Goodbye!");

    }

}
