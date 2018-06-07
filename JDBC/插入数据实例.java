package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

            Connection conn = null;
            Statement stmt = null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stmt = conn.createStatement();
        //String sql = "";
        //ResultSet rs = stmt.executeQuery("SELECT app_name, country FROM " + bb);
        String sql = "INSERT INTO websites " +
                "VALUES ('6', 'soogle', 'https://www.googwle.cm/', '151', 'USA')"+
                "('7', '淘宝', 'https://www.taobao.com/', '13', 'CN'), " +
                "('8', '菜鸟教程', 'http://www.runoob.com', '5892', ''), " +
                "('9', '微博', 'http://weibo.com/', '20', 'CN'), " +
                "('10', 'Facebook', 'https://www.facebook.com/', '3', 'USA')";
        //记得在" "中的类型要加' '
        stmt.executeUpdate(sql);//是executeUpdate而不是executeQuery！
        //不需要新建类型ResultSet rs = stmt.executeQuery(sql);
        //不需要while(rs.next())了
       
        //5. 清理环境
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
