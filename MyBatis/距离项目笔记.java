// 路径：/Users/yelong/IdeaProjects/demo3

@RestController
@RequestMapping("/api")
//一定要加这个绑定，给它一个进入的路径，之前注释掉了，会报错。

/*之前用的8080端口，运行时报错，原因是在终端开了Tomcat，而Tomcat占用的端口是8080，一直没有关Tomcat，而外面也看不出来开了没有，所以不好检查出来。
  解决方法：1.用别的端口；2.在终端kill掉：输入 ps -ef | grep tomcat，看看有没有在运行，（同理也可以 ps -ef | grep java）然后显示某数字 再输入，
  sudo kill -9 21830  （21830是此数字），就可以了。*/

//参数传递问题  
public User aa(@RequestParam("city_from") String city_from)throws Exception {
    
String sql = "SELECT d.flight_distance FROM city c1 INNER JOIN distance d INNER JOIN city c2" +
        " where c1.city_name =  city_fromand d.source_city_id ="+c1.city_id;
/* 这里变量从postman传进来没问题，但组装成string sql就有问题了，可以就在String sql的前后设置两个断点，观察。
看出组装结果是 "select....id = 北京..."，但是上文传进来的是'北京'，所以应该给变量加两个单引号，怎么做呢，直接给变量加单引号成'c1.city_id'肯定不对
所以在变量之前的双引号前加一个,写成"d.source_city_id ='"+c1.city_id+"'";就可以了。

要注意 1. 空格，空格的存在会对应不同的对象；  2.存在 mysql注入 问题！

所以 最好写成：*/
  String sql = "SELECT d.flight_distance FROM city c1 INNER JOIN distance d INNER JOIN city c2" +
                    "                    where c1.city_name = ? and d.source_city_id = c1.city_id and  c2.city_name = ? and d.destination_city_id = c2.city_id";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,city_from);
            st.setString(2,city_to);
            ResultSet rs = st.executeQuery();

