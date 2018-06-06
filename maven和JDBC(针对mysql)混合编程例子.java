package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;
@RestController
public class Api2Controller {
    public static final String URL = "jdbc:mysql://localhost:3306/newbase";
    public static final String USER = "root";
    public static final String PASSWORD = "12345678";

    @RequestMapping("/test")

    public static void aa(@RequestParam("bb") String bb) throws Exception {
        //可以在下一行打一个断点，验证postman的参数有没有被传回来（这时候就不能点run了，而要点run旁的debug）
        //idea里下方的debugger和console窗口都要经常看一看。
        Connection conn = null;
        Statement stmt = null;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        stmt = conn.createStatement();
        String sql = "";
        ResultSet rs = stmt.executeQuery("SELECT app_name, country FROM " + bb);//参数是一个string型，我一开始写错了，写成"SELECT app_name, country FROM bb"
        System.out.println(rs);

        while (rs.next()) {
            
            // 通过字段检索
            String name = rs.getString("app_name");
            String country = rs.getString("country");

            // 输出数据           
            System.out.print(", app名: " + name);
            System.out.print(", 国家: " + country);
            System.out.print("\n");
        }
    }
}
