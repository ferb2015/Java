// 路径：/Users/yelong/IdeaProjects/demo3

@RestController
@RequestMapping("/api")
//一定要加这个绑定，给它一个进入的路径，之前注释掉了，会报错。

/*之前用的8080端口，运行时报错，原因是在终端开了Tomcat，而Tomcat占用的端口是8080，一直没有关Tomcat，而外面也看不出来开了没有，所以不好检查出来。
  解决方法：1.用别的端口；2.在终端kill掉：输入 ps -ef | grep tomcat，看看有没有在运行，（同理也可以 ps -ef | grep java）然后显示某数字 再输入，
  sudo kill -9 21830  （21830是此数字），就可以了。*/
