settings.xml配置文件里可以添加一行，这样就可以优先访问公司的服务器，公司服务器上有很多已经加载好的东西，这样下载比用公网快。如果要把笔记本带回家，要将settings.xml改一下，改成公网访问的。

    cd .m2
    cat settings.xml
    
    mv settings.xml settings.xml.guazi      #这是把setting.xml move到一个本不存在的文件，或对文件或目录重新命名
    cp settings.xml.guazi settings.xml
    rm settings.xml         #remove setting.xml 程序允许只去寻找setting.xml，如果本地有，优先运行已经存在的，没有的话，会加载它自己的。
所以下次 想要运行公司服务器，只要把settings.xml.guazi移到settings.xml，再copy即可。
其实两者区别，就是多了一行url，<url>http://nexus.dns.guazi.com:8081/nexus/repository/public-archetype/</url>
