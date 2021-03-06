路径：/Users/yelong/IdeaProjects/demo3
#### 绑定

    @RestController
    @RequestMapping("/api")

一定要加这个绑定，给它一个进入的路径，之前注释掉了，会报错。
#### 8080端口
之前用的8080端口，运行时报错，原因是在终端开了```Tomcat```，而Tomcat占用的端口是8080，一直没有关Tomcat，而外面也看不出来开了没有，所以不好检查出来。
  解决方法：1.用别的端口；2.在终端kill掉：输入 ```ps -ef | grep tomcat```，看看有没有在运行，（同理也可以 ps -ef | grep java）然后显示某数字 再输入，```sudo kill -9 21830 ``` （21830是此数字），就可以了。

#### 参数传递问题  

    public User aa(@RequestParam("city_from") String city_from)throws Exception {    
    String sql = "SELECT d.flight_distance FROM city c1 INNER JOIN distance d INNER JOIN city c2" +
        " where c1.city_name =  city_fromand d.source_city_id ="+c1.city_id;
        
这里变量从```postman```传进来没问题，但组装成string sql就有问题了，可以就在String sql的前后设置两个断点，观察。
看出组装结果是 "select....id = 北京..."，但是上文传进来的是'北京'，所以应该给变量加两个单引号，怎么做呢，直接给变量加单引号成'c1.city_id'肯定不对
所以在变量之前的双引号前加一个,写成```"d.source_city_id ='"+c1.city_id+"'"```就可以了。

##### 要注意 1. 空格，空格的存在会对应不同的对象；  2.存在 mysql注入 问题！ 左引号右引号可能被滥用 
见  https://baike.baidu.com/item/SQL%E6%B3%A8%E5%85%A5

所以 最好写成：
    
    String sql = "SELECT d.flight_distance FROM city c1 INNER JOIN distance d INNER JOIN city c2" +
    " where c1.city_name = ? and d.source_city_id = c1.city_id and  c2.city_name = ? and d.destination_city_id = c2.city_id";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,city_from);
            st.setString(2,city_to);
            ResultSet rs = st.executeQuery();
有很多很多已经封装好的包，就可以省去很多麻烦写，比如上面的PreparedStatement，如果不调用，则自己要写很多匹配项，防止mysql注入问题，确保错误输入
  不会形成能匹配的string语句。
  
包的大全：  https://www.apache.org/      

####  Json 对象概念 解释：
Json对象中的每一个属性，可以是基础属性，也可以是对象（对象中还有基础属性和对象，层层包装）。  


####  mapper中是增删改查具体写法，java类中写基本属性，service写事务管理，调用mapper进行一条条指令操作。 


####  拦截器，过滤器 解释
比如在controller之前就写相当于写 if，如果上层比如密码输入不对，都不会执行下面的语句了，只不过有很多if，包装成了一个包。

#### java基础  书写类原则 set get ：
最后在postman输入，得到返回
  
      {
        "code": 0,
        "message": "ok",
        "data": {
            "distance": 50
        }
    }
定义一个类class，属性是private，不允许改变，但是其他类想要调用这些方法，就需要原class提供public接口，这就是set和get，让其他对象能用，提供接口。

    public class User {
        private int code;
        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }
    }
 
上文的花括号{}，说明是一个对象，即JSon对象返回的其中一个属性，是对象。
建立这个对象的方法：在public class User{}中新建类对象
    
    public class User {
        private Abb data;
        public Abb getData() {
            return data;
        }
        public void setData(Abb data) {
            this.data = data;
        }
    }
然后新建一个类Abb，写入：

    package com.yiibai.mybatis.models;

    public class Abb {
        private int distance;

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Abb{" +
                    "distance=" + distance +
                    '}';
        }
    }
注意，两个类都要写set、get，都要提供接口。

idea中自带快捷写出set、get的快捷键：```control + enter```
idea中自带快捷补全要引用import的类名的快捷键```option + enter```

最后在测试文件中写入：
    
    User u = new User();
    Abb a = new Abb();
    a.setDistance(flight_distance);
    u.setData(a);

#### HTTP知识点  post、get
post加了一个body。
对于数据表的列数很少，参数很少的适合，多的就不合适了。
因为每次增加表内容insert后，组成的是形如```http://127.0.0.1:9999/api/distance?city_to=上海&city_from=北京```的形式，
（url、host、请求方式、请求路径、header、请求体、Content-Type）
但是有些接收方无法接收很长的路径，所以用get就不合适了。
所以要用到post，包装一层，最后形成路径长度很短，但是接收方一打开里面有一大堆参数。


#### 技术文档

按照流程图的思想，写伪代码，根据伪代码每句填充几行代码，一般伪代码思路没错，真实的代码八九不离十。

