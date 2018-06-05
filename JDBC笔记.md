## Java数据库连接
有很多不同的数据库，比如oracle的数据库、mysql，等等。
### JDBC 编程步骤
#### 下载mysql的jar包
```mysql-connector-java-5.1.28.jar```，然后在idea工程里引入jar包：```File - project structure - Libraries - + （选java）- 
找到路径下的.jar文件```
#### 加载驱动程序：（对于mysql数据库）

    Class.forName(driverClass)
    //加载MySql驱动
    Class.forName("com.mysql.jdbc.Driver")
    //或者加载Oracle驱动
    Class.forName("oracle.jdbc.driver.OracleDriver")
    
#### 获得数据库连接：

    DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/数据库名", "root", "password");//数据库名写要调用的数据库名 每个不同
    //或者DriverManager.getConnection("jdbc:mysql://localhost/数据库名", "root", "password")
#### 创建Statement\PreparedStatement对象：

    conn.createStatement();
    conn.prepareStatement(sql);
    
    
#### 查询
操作数据库，实现增删改查
 #### 1、 创建```statement```对象。
在使用```Statement```对象执行SQL语句之前，需要使用```Connection```对象的```createStatement()```方法创建一个```Statement```对象

        Statement stmt = conn.createStatement();
在创建```Statement```对象后，可以使用它来执行一个SQL语句，它有三个执行方法可以执行。它们分别是 

 - ```boolean execute (String SQL) ```： 如果可以检索到ResultSet对象，则返回一个布尔值true; 否则返回false。使用此方法执行SQLDDL语句或需要使用真正的动态SQL，可使用于执行创建数据库，创建表的SQL语句等等。
 - ```int executeUpdate (String SQL)```: 返回受SQL语句执行影响的行数。使用此方法执行预期会影响多行的SQL语句，例如:INSERT，UPDATE或DELETE语句。
 - ```ResultSet executeQuery(String SQL)```：返回一个```ResultSet```对象。 当您希望获得结果集时，请使用此方法，就像使用```SELECT```语句一样。

        ResultSet rs = stmt.executeQuery("SELECT app_name, country FROM apps");
        // 或者 创建sql语句
        String sqlString = "select * from student";       
        pst = conn.prepareStatement(sqlString);
        rSet = pst.executeQuery();
#### 2、PreparedStatement对象 
。。。。
#### 3、CallableStatement对象

#### JDBC结果集 ```ResultSet```
SQL语句执行后从数据库查询读取数据，返回的数据放在结果集中。```SELECT```语句用于从数据库中选择行并在结果集中查看它们的标准方法。 ```java.sql.ResultSet```接口表示数据库查询的结果集。

    ResultSet rs = stmt.executeQuery("SELECT app_name, country FROM apps");
#### 异常
```SQLException```中的方法
一个```SQLException```类既可以发生在驱动程序和数据库中。当这样的异常时，```SQLException```类型的对象将被传递到```catch```子句。
通过```SQLException```对象有以下几种方法可用于获取更多的关于异常的信息：


      getErrorCode()	# 获取与异常关联的错误代码
      getMessage()	# 获取JDBC驱动程序的错误处理错误消息，或获取Oracle错误代码和数据库的错误消息。
      getSQLState()	# 获取XOPEN SQLSTATE字符串。对于JDBC驱动程序错误，从该方法返回的可能是无用的信息。对于一个数据库错误，返回一个五位的XOPEN SQLSTATE代码。这种方法可以返回null。
      getNextException()	# 获取异常链中的下一个Exception对象
      printStackTrace()	# 打印当前的异常，或也可以抛出，并回溯到标准错误流
      printStackTrace(PrintStream s)	# 打印此抛出对象及其回溯到指定的打印流
      printStackTrace(PrintWriter w)	# 打印此抛出对象及其回溯到指定打印写入流
