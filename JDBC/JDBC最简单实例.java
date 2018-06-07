//参考自https://www.yiibai.com/jdbc/jdbc-statements.html#article-start     讲得比较详细
package com.company; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {
    
    public static final String URL = "jdbc:mysql://localhost:3306/newbase?useUnicode=true&characterEncoding=UTF-8";
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
           ResultSet rs = stmt.executeQuery("SELECT app_name, country FROM apps");//ResultSet executeQuery(String SQL)：返回一个ResultSet对象。 当您希望获得结果集时，请使用此方法，就像使用SELECT语句一样。
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
         }finally{//再确保一次已经close了
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
    }
}
