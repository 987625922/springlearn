1、检查是否已经安装过mysql，执行命令
[root@localhost /]# rpm -qa | grep mysql
 

 从执行结果，可以看出我们已经安装了mysql-libs-5.1.73-5.el6_6.x86_64，执行删除命令
[root@localhost /]# rpm -e --nodeps mysql-libs-5.1.73-5.el6_6.x86_64
再次执行查询命令，查看是否删除

[root@localhost /]# rpm -qa | grep mysql
 

 2、查询所有Mysql对应的文件夹
 
[root@localhost /]# whereis mysql mysql: /usr/bin/mysql /usr/include/mysql [root@localhost lib]# find / -name mysql /data/mysql /data/mysql/mysql
 
删除相关目录或文件

[root@localhost /]# rm -rf /usr/bin/mysql /usr/include/mysql /data/mysql /data/mysql/mysql
验证是否删除完毕

[root@localhost /]# whereis mysql mysql: [root@localhost /]# find / -name mysql [root@localhost /]#
3、检查mysql用户组和用户是否存在，如果没有，则创建
 
[root@localhost /]# cat /etc/group | grep mysql [root@localhost /]# cat /etc/passwd |grep mysql [root@localhost /]# groupadd mysql [root@localhost /]# useradd -r -g mysql mysql [root@localhost /]#
 
4、从官网下载是用于Linux的Mysql安装包
下载命令：

[root@localhost /]# wget https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz

==============



1、在执行wget命令的目录下或你的上传目录下找到Mysql安装包：mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz
执行解压命令：

[root@localhost /]# tar xzvf mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz [root@localhost /]# ls mysql-5.7.24-linux-glibc2.12-x86_64 mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz
解压完成后，可以看到当前目录下多了一个解压文件，移动该文件到/usr/local/下，并将文件夹名称修改为mysql。

2、在/usr/local/mysql目录下创建data目录

4、编译安装并初始化mysql,务必记住初始化输出日志末尾的密码（数据库管理员临时密码）.T6Jhj(Gh8zX

[root@localhost /]# cd /usr/local/mysql/bin [root@localhost bin]# ./mysqld --initialize --user=root --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql

运行的时候找不到libaio.so.1
就运行yum install -y libaio.so.1

  　注意：记录日志最末尾位置root@localhost:后的字符串，此字符串为mysql管理员临时登录密码。
 6、编辑配置文件my.cnf，添加配置如下
  +1e4,Xy3MG+P
  
 [root@localhost bin]# vi /etc/my.cnf [mysqld] 
 datadir=/usr/local/mysql/data 
 port = 3306 
 sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
 symbolic-links=0 
 max_connections=400 innodb_file_per_table=1 #表名大小写不明感，敏感为 lower_case_table_names=1
 user=root
  
 7、启动mysql服务器
 
 [root@localhost /]# /usr/local/mysql/support-files/mysql.server start
 显示如下结果，说明数据库安装成功
 
  
  如果出现如下提示信息
 Starting MySQL... ERROR! The server quit without updating PID file
 查看是否存在mysql和mysqld的服务，如果存在，则结束进程，再重新执行启动命令
 
 　#查询服务
 
 ps -ef|grep mysql ps -ef|grep mysqld
 　#结束进程
 
 kill -9 PID
 　#启动服务
 
 
 /usr/local/mysql/support-files/mysql.server start
 
 
  8、添加软连接，并重启mysql服务
 [root@localhost /]# ln -s /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql [root@localhost /]# ln -s /usr/local/mysql/bin/mysql /usr/bin/mysql [root@localhost /]# service mysql restart
 9、登录mysql，修改密码(密码为步骤5生成的临时密码)
 
 [root@localhost /]# mysql -u root -p Enter password: mysql>set password for root@localhost = password('你的密码');
  
 
 
  
 10、开放远程连接
 
 mysql>use mysql; msyql>update user set user.Host='%' where user.User='root'; mysql>flush privileges;
 
 
  11、设置开机自动启动
 　1、将服务文件拷贝到/etc/init.d下，并重命名为mysql
 
 [root@localhost /]# cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysqld
 　2、赋予可执行权限
 
 [root@localhost /]# chmod +x /etc/init.d/mysqld
 　3、添加服务
 
 [root@localhost /]# chkconfig --add mysqld
     4、显示服务列表
 
  
 [root@localhost /]# chkconfig --list
 
 ============== 防火墙
 查看防火墙某个端口是否开放
 firewall-cmd --query-port=80/tcp
 
 开放防火墙端口80
 firewall-cmd --zone=public --add-port=80/tcp --permanent
 
 关闭80端口
 
 firewall-cmd --zone=public --remove-port=80/tcp --permanent  
 
 配置立即生效
 firewall-cmd --reload 
 查看防火墙状态
 systemctl status firewalld
 
 关闭防火墙
 systemctl stop firewalld
 
 打开防火墙
 systemctl start firewalld
 
 开放一段端口
 firewall-cmd --zone=public --add-port=8121-8124/tcp --permanent
 
 查看开放的端口列表
 firewall-cmd --zone=public --list-ports
 
 ========= redis
 # wget http://download.redis.io/releases/redis-2.8.24.tar.gz
 # tar xzf redis-2.8.24.tar.gz
 # cd redis-2.8.24
 # make
如果make找不到gcc就yum install gcc
如果make找不到fatal error: jemalloc/jemalloc.h就使用make MALLOC=libc
 
 执行完 make 命令后，redis-2.8.24 的 src 目录下会出现编译后的 redis 服务程序 redis-server，还有用于测试的客户端程序 redis-cli：
 
 下面启动 redis 服务：
 
 # cd src
 # ./redis-server
 
 #vim conf/redis.conf
 把daemonize设置为yes
 
 #vim /etc/init.d/redis  --创建脚本文件
 
 这里我的redis安装路径是/usr/local/src/redis-3.0.3,端口为8530,然后redis.conf文件在/etc下面，根据redis自带的启动脚本进行修改，它默认的路径是在$redis/utils/redis_init_script，$redis是redis的安装路径
 
 #!/bin/bash
 #chkconfig: 22345 10 90
 #description: Start and Stop redis
 
 3、写完记得修改文件权限
 
 #chmod +x /etc/init.d/redis
 
 4、把脚本添加到系统服务列表
 
 #chkconfig --add redis
 #chkconfig --list   //查看所有注册的脚本文件
 
 =========== zookeeper
 $ wget https://archive.apache.org/dist/zookeeper/zookeeper-3.4.10/zookeeper-3.4.10.tar.gz
 $ tar -zxvf zookeeper-3.4.14.tar.gz
 $ cd zookeeper-3.4.14
 $ cd conf/
 $ cp zoo_sample.cfg zoo.cfg
 $ cd ..
 $ cd bin/
 $ sh zkServer.sh start

//zookeeper开机自启
 
 #!/bin/bash
 #chkconfig:2345 20 90
 #description:zookeeper
 #processname:zookeeper
 export JAVA_HOME=/usr/local/JAVA
 case $1 in
           start) su root /data/zookeeper-3.4.11/bin/zkServer.sh start;;
           stop) su root /data/zookeeper-3.4.11/bin/zkServer.sh stop;;
           status) su root /data/zookeeper-3.4.11/bin/zkServer.sh status;;
           restart) su root /data/zookeeper-3.4.11/bin/zkServer.sh restart;;
           *)  echo "require start|stop|status|restart"  ;;
 esac
 
  3、写完记得修改文件权限
  
  #chmod +x /etc/init.d/zookeeper
 
 ##============= java 
 wget https://download.oracle.com/otn/java/jdk/8u311-b11/4d5417147a92418ea8b615e228bb6935/jdk-8u311-linux-x64.tar.gz?AuthParam=1637552137_11068bc376d461e657a22ce6b050a7a9
 
 tar -zxvf jdk-8u311-linux-x64.tar.gz
 
mv jdk-8u311-linux-x64 /usr/local/java

设置环境变量
vim /etc/profile

在末尾添加
export JAVA_HOME=/usr/local/java/jdk1.8.0_171
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH

使环境变量生效
source /etc/profile

添加软链接
ln -s /usr/local/java/jdk1.8.0_171/bin/java /usr/bin/java