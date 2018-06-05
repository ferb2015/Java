package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student1 {

    public static final String URL = "jdbc:mysql://localhost:3306/newbase";
    public static final String USER = "root";
    public static final String PASSWORD = "12345678";

    public static void main(String[] args) throws Exception {
         Connection conn = null;
         Statement stmt = null;
         try {
           //1.加载驱动程序
           Class.forName("com.mysql.jdbc.Driver");
           //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
           //3.操作数据库，实现增删改查 查询
            stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT app_name, country FROM apps");
           /*或者写成 String sql;  
           sql = "SELECT app_name, country FROM apps";
           ResultSet rs = stmt.executeQuery();*/
           //4. 得到和处理结果集  如果有数据，rs.next()返回true
           while(rs.next()){
               System.out.println(rs.getString("app_name")+" 姓名："+rs.getString("country"));
               /*或者写成 String app_name = rs.getString("app_name");
               System.out.println(app_name);*/
            }
            //5. 清理环境
            rs.close();
            stmt.close();
            conn.close();
         } catch (SQLException se){
            //JDBC 操作错误
            se.printStackTrace();
         } catch (Exception e){
            // Class.forName 错误
            e.printStackTrace();
         }
    }
}
