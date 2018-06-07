   ### github上clone到本地：
   
   在终端输入
   
    $ git
    
    usage: git [--version] [--help] [-C <path>] [-c name=value]
           [--exec-path[=<path>]] [--html-path] [--man-path] [--info-path]
           [-p | --paginate | --no-pager] [--no-replace-objects] [--bare]
           [--git-dir=<path>] [--work-tree=<path>] [--namespace=<name>]
           <command> [<args>]

    These are common Git commands used in various situations:

    start a working area (see also: git help tutorial)
       clone      Clone a repository into a new directory
       init       Create an empty Git repository or reinitialize an existing one

    work on the current change (see also: git help everyday)
       add        Add file contents to the index
       mv         Move or rename a file, a directory, or a symlink
       reset      Reset current HEAD to the specified state
       rm         Remove files from the working tree and from the index

    examine the history and state (see also: git help revisions)
       bisect     Use binary search to find the commit that introduced a bug
       grep       Print lines matching a pattern
       log        Show commit logs
       show       Show various types of objects
       status     Show the working tree status

    grow, mark and tweak your common history
       branch     List, create, or delete branches
       checkout   Switch branches or restore working tree files
       commit     Record changes to the repository
       diff       Show changes between commits, commit and working tree, etc
       merge      Join two or more development histories together
       rebase     Reapply commits on top of another base tip
       tag        Create, list, delete or verify a tag object signed with GPG

    collaborate (see also: git help workflows)
       fetch      Download objects and refs from another repository
       pull       Fetch from and integrate with another repository or a local branch
       push       Update remote refs along with associated objects
       
  在github上新建一个仓库，复制网址clone with https，输入到终端某个路径里 比如cd temp/
  
    git clone https://github.com/mybatis/spring.git

就clone下来了，可以```ls```或```log```查看
终端输入（最常用的 每一次都要执行的：add、commit）
  
    git add .
    git  commit -m "add file"     #后面"本次提交的注释 每次注释本次修改大致内容"
    
  如果user name 和password忘了 ：
  
    git commit --amend --reset-author
    
   移除存在的github库
    
    git config --global --edit   
    mv .gitconfig .gitconfig.bak
    
   查看信息 用户名邮箱等：
   
    ls .git*
    cat .gitconfig 
  
  到路径里后，新建文件：
  
    touch a.txt
    vim a.txt
    
    git add .
    git commit -m "add a.txt
    
查看还有哪些步骤没做/要做

    git status
 
 东西写完了，要提交到github上：
 
    git push
  
  ### 本地提交到github上
  
  1. 可以把idea的project拖到那个文件夹里，
  在idea界面中，对左边的大文件夹，右键git  - repository - add/commit。
  再push上去，就可以了。
  
  2. idea的上方菜单栏 - ```VCS - Import into Version Control - Share Project on Github - token（会话秘匙，下次就不用输密码了） - 输入用户名密码 - new repositories``` 。
  在idea里也可以查看终端：界面下方那栏有一个terminal。然后 ```git add .和git commit 和 git push```
  ### [!]idea 下栏切换到 Version Control 点 log 能看到历史记录，如果要回到前一版本，点选某个版本，右键 Copy Revision Number,复制编号
  ### 然后在切换到下栏终端 先git stash存储一下当前，再git reset 粘贴刚才的编号，就可以恢复前面的版本了。
  ### [!] git push 之前，要先git pull一下，因为可能有多人修改文件，pull可以查看最新修改版本，然后可选merge合并修改的版本。
  
  ### github上同步到本地
  查看远程仓库
   
      git remote -v
从远程获取最新版本到本地

      git fetch origin master
 git fetch origin master 这句的意思是：从远程的origin仓库的master分支下载代码到本地的origin master
 
 比较本地的仓库和远程参考的区别
 
      git log -p master.. origin/master
      
   把远程下载下来的代码合并到本地仓库，远程的和本地的合并
   
      git merge origin/master
