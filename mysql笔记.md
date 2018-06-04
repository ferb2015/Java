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
