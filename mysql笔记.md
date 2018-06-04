## mysql 安装
装好了之后，终端输入```mysql -u root -p```返回```-bash: mysql: command not found```。但是```ls```能看见是有的

改成```./mysql -u root -p```就可以了，```.```是当前路径的意思，所以./mysql就可以了，等效于```/usr/local/mysql/bin/mysql -u root -p```。
因为mysql的path搜寻，是从根目录开始搜索，所以即使一直cd进入了该目录，输入mysql依然是从根目录找起。```ls -asl```

所以可以把mysql的路径添加到搜索的path里，这样它下次就会除了搜索根目录起的path，还会搜索添加的path。

    vim ~/.bash_profile
在bash_profile里写入：


    export MYSQL=/usr/local/mysql/bin
    export PATH=${PATH}:${MYSQL}

第一行```MYSQL```是一个变量，添加到path中，可以添加很多个path，用```：```连接。

```esc :wq```返回后，输入：

    source ~/.bash_profile 
    
然后就可以直接使用mysql作为输入路径了

    mysql -uroot -p


备忘：我的密码12345678


## mysql 语句
不分大小写

#### 进入mysql:

    mysql -uroot -p
在终端的mysql下，输入

    mysql> show databases;        #注意有;
#### 退出mysql

    quit
#### 导入某路径的.sql文件

    mysql -u root -p
    create database name;
    use name;
    source 『将.sql文件直接拖拽至终端，自动补全其文件目录』

#### 导出.sql文件

    cd 『打开要将.sql文件生成的文件位置』
    mysqldump -u root -p database_name>sql_name.sql

 #### 创建新表：
 
    CREATE TABLE newtable(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(40) NOT NULL,
    submission_date DATE,
    PRIMARY KEY ( id )
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;
如果你不想字段为 NULL 可以设置字段的属性为 NOT NULL， 在操作数据库时如果输入该字段的数据为NULL ，就会报错。

AUTO_INCREMENT定义列为自增的属性，一般用于主键，数值会自动加1。每次添加表内容，就会自增1，所以不用特地写id的value也行。

PRIMARY KEY关键字用于定义列为主键。 您可以使用多列来定义主键，列间以逗号分隔。

ENGINE 设置存储引擎，CHARSET 设置编码。

通过```show tables;```和```show columns from table;```查看细节

#### 读取数据表
    
        select * from tablename;
#### 插入数据
        
        use RUNOOB;
        INSERT INTO runoob_tbl 
        (runoob_title, runoob_author, submission_date)
        VALUES
        ("学习 PHP", "菜鸟教程", NOW());
如果一条SQL语句太长，我们可以通过回车键来创建一个新行来编写 SQL 语句，SQL 语句的命令结束符为分号 ```;```。

在以上实例中，我们并没有提供 runoob_id 的数据，因为该字段我们在创建表的时候已经设置它为 AUTO_INCREMENT(自动增加) 属性。 所以，该字段会自动递增而不需要我们去设置。实例中 NOW() 是一个 MySQL 函数，该函数返回日期和时间。

#### 查询 where子句

    select * from runoob_tbl where runoob_author ='数据库';
    
    SELECT field1, field2,...fieldN FROM table_name1, table_name2...
    [WHERE condition1 [AND [OR]] condition2.....

#### update 更新替换修改某值

    UPDATE runoob_tbl SET runoob_title='学习 C++' WHERE runoob_id=3;      #把id=3的行内容的title改成了'学习c++'
    
    UPDATE table_name SET field1=new-value1, field2=new-value2 [WHERE Clause]
    
你可以同时更新一个或多个字段。你可以在 WHERE 子句中指定任何条件。你可以在一个单独表中同时更新数据。


#### LIKE 子句 查询部分含有的，而不是全匹配的，

 runoob_tbl 表中获取 runoob_author 字段中以 COM 为结尾的的所有记录：
 
    SELECT * from runoob_tbl  WHERE runoob_author LIKE '%COM';
    
#### UNION 操作符
联系多个表，UNION 操作符用于连接两个以上的 SELECT 语句的结果组合到一个结果集合中。多个 SELECT 语句会删除重复的数据。

下面的 SQL 语句从 "Websites" 和 "apps" 表中选取所有不同的country（只有不同的值）：

    SELECT country FROM Websites
    UNION
    SELECT country FROM apps
    ORDER BY country;
    
使用```UNION ALL``` 来选取重复的值：

    SELECT country FROM Websites
    UNION ALL
    SELECT country FROM apps
    ORDER BY country;
    
带有 WHERE 的 SQL UNION ALL：

下面的 SQL 语句使用 UNION ALL 从 "Websites" 和 "apps" 表中选取所有的中国(CN)的数据（也有重复的值）：

    SELECT country, name FROM Websites
    WHERE country='CN'
    UNION ALL
    SELECT country, app_name FROM apps
    WHERE country='CN'
    ORDER BY country;
    
#### 排序 ORDER BY 
    SELECT field1, field2,...fieldN table_name1, table_name2...
    ORDER BY field1, [field2...] [ASC [DESC]]
你可以使用任何字段来作为排序的条件，从而返回排序后的查询结果。

你可以设定多个字段来排序。

你可以使用 ```ASC``` 或 ```DESC``` 关键字来设置查询结果是按升序或降序排列。 默认情况下，它是按升序排列。

你可以添加``` WHERE...LIKE ```子句来设置条件。
    
    SELECT * from runoob_tbl ORDER BY submission_date ASC;

#### GROUP BY 
GROUP BY 语句根据一个或多个列对结果集进行分组。

在分组的列上我们可以使用 COUNT, SUM, AVG,等函数。

    >http://www.runoob.com/mysql/mysql-group-by-statement.html

GROUP BY 语法

    SELECT column_name, function(column_name)
    FROM table_name
    WHERE column_name operator value
    GROUP BY column_name;

使用 GROUP BY 语句 将数据表按名字进行分组，并统计每个人有多少条记录：
    
    SELECT name, COUNT(*) FROM   employee_tbl GROUP BY name;
    
使用 WITH ROLLUP

WITH ROLLUP 可以实现在分组统计数据基础上再进行相同的统计（SUM,AVG,COUNT…)      就是最后多了一行统计值

我们将以上的数据表按名字进行分组，再统计每个人登录的次数：

    SELECT name, SUM(singin) as singin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;
    
    SELECT coalesce(name, '总数'), SUM(singin) as singin_count FROM  employee_tbl GROUP BY name WITH ROLLUP;
    
#### 连接的使用 
多表， 从多个数据表中读取数据。

JOIN 在两个或多个表中查询数据。

你可以在 SELECT, UPDATE 和 DELETE 语句中使用 Mysql 的 JOIN 来联合多表查询。

JOIN 按照功能大致分为如下三类：

 - INNER JOIN（内连接,或等值连接）：获取两个表中字段匹配关系的记录。 取交集
 - LEFT JOIN（左连接）：获取左表所有记录，即使右表没有对应匹配的记录。
 - RIGHT JOIN（右连接）： 与 LEFT JOIN 相反，用于获取右表所有记录，即使左表没有对应匹配的记录。
 
   INNER JOIN:
  
 使用MySQL的INNER JOIN(也可以省略 INNER 使用 JOIN，效果一样)来连接以上两张表来读取runoob_tbl表中所有runoob_author字段在tcount_tbl表对应的runoob_count字段值：
       
       mysql> SELECT a.runoob_id, a.runoob_author, b.runoob_count FROM runoob_tbl a INNER JOIN tcount_tbl b ON a.runoob_author = b.runoob_author;
等价于
   
        mysql> SELECT a.runoob_id, a.runoob_author, b.runoob_count FROM runoob_tbl a, tcount_tbl b WHERE a.runoob_author = b.runoob_author;
        
