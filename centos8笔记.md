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

4、编译安装并初始化mysql,务必记住初始化输出日志末尾的密码（数据库管理员临时密码）kZf3;X#xe%i>

[root@localhost /]# cd /usr/local/mysql/bin [root@localhost bin]# ./mysqld --initialize --user=root --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql

运行的时候找不到libaio.so.1
就运行yum install -y libaio.so.1

  　注意：记录日志最末尾位置root@localhost:后的字符串，此字符串为mysql管理员临时登录密码。
 6、编辑配置文件my.cnf，添加配置如下
  
 [root@localhost bin]# vi /etc/my.cnf 
 [mysqld] 
 datadir=/usr/local/mysql/data 
 port = 3306 
 sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 
 symbolic-links=0 
 max_connections=400 
 innodb_file_per_table=1 #表名大小写不明感，敏感为 lower_case_table_names=1
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
 
 [root@localhost /]# mysql -u root -p Enter password: 
 mysql>set password for root@localhost = password('你的密码');
  
 
 
  
 10、开放远程连接
 
 mysql>use mysql; 
 msyql>update user set user.Host='%' where user.User='root'; 
 mysql>flush privileges;
 
 
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

#================== nginx
1.安装编译工具及库文件
yum -y install make zlib zlib-devel gcc-c++ libtool  openssl openssl-devel

2.首先要安装 PCRE
  PCRE 作用是让 Nginx 支持 Rewrite 功能。
  
  1、下载 PCRE 安装包，下载地址： http://downloads.sourceforge.net/project/pcre/pcre/8.35/pcre-8.35.tar.gz
  进入安装包目录
  mv pcre-8.35 /usr/local 
  [root@bogon src]# cd pcre-8.35
  4、编译安装 
  
  [root@bogon pcre-8.35]# ./configure
  [root@bogon pcre-8.35]# make && make install
  5、查看pcre版本
  
  [root@bogon pcre-8.35]# pcre-config --version

3.安装nginx
wget http://nginx.org/download/nginx-1.15.4.tar.gz

安装包

[root@bogon src]# tar zxvf nginx-1.6.2.tar.gz
3、进入安装包目录

[root@bogon src]# cd nginx-1.6.2
4、编译安装

[root@bogon nginx-1.6.2]# ./configure --prefix=/usr/local/webserver/nginx --with-http_stub_status_module --with-http_ssl_module --with-pcre=/usr/local/src/pcre-8.35
[root@bogon nginx-1.6.2]# make
[root@bogon nginx-1.6.2]# make install
5、查看nginx版本

[root@bogon nginx-1.6.2]# /usr/local/webserver/nginx/sbin/nginx -v
到此，nginx安装完成。

启动
/usr/local/webserver/nginx/sbin/nginx

[root@bogon conf]# /usr/local/webserver/nginx/sbin/nginx -t
以下包含了 Nginx 常用的几个命令：
/usr/local/webserver/nginx/sbin/nginx -s reload            # 重新载入配置文件
/usr/local/webserver/nginx/sbin/nginx -s reopen            # 重启 Nginx
/usr/local/webserver/nginx/sbin/nginx -s stop              # 停止 Nginx


6. nginx设置成开机自动启动服务

1)在/etc/init.d下创建文件nginx，文件的内容拷贝官网内容，主要修改下面两个参数的值
nginx="/usr/sbin/nginx" #修改成nginx执行程序的路径。
NGINX_CONF_FILE="/etc/nginx/nginx.conf" #修改成nginx.conf文件的路径。
目前我的环境修改成如下内容
nginx=”/usr/local/nginx/sbin/nginx”
NGINX_CONF_FILE=”/usr/local/nginx/conf/nginx.conf”

#!/bin/sh
#
# nginx - this script starts and stops the nginx daemon
#
# chkconfig:   - 85 15
# description:  NGINX is an HTTP(S) server, HTTP(S) reverse \
#               proxy and IMAP/POP3 proxy server
# processname: nginx
# config:      /etc/nginx/nginx.conf
# config:      /etc/sysconfig/nginx
# pidfile:     /var/run/nginx.pid
 
# Source function library.
. /etc/rc.d/init.d/functions
 
# Source networking configuration.
. /etc/sysconfig/network
 
# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0
 
nginx="/usr/local/nginx/sbin/nginx"
prog=$(basename $nginx)
 
NGINX_CONF_FILE="/usr/local/nginx/conf/nginx.conf"
 
[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx
 
lockfile=/var/lock/subsys/nginx
 
make_dirs() {
   # make required directories
   user=`$nginx -V 2>&1 | grep "configure arguments:.*--user=" | sed 's/[^*]*--user=\([^ ]*\).*/\1/g' -`
   if [ -n "$user" ]; then
      if [ -z "`grep $user /etc/passwd`" ]; then
         useradd -M -s /bin/nologin $user
      fi
      options=`$nginx -V 2>&1 | grep 'configure arguments:'`
      for opt in $options; do
          if [ `echo $opt | grep '.*-temp-path'` ]; then
              value=`echo $opt | cut -d "=" -f 2`
              if [ ! -d "$value" ]; then
                  # echo "creating" $value
                  mkdir -p $value && chown -R $user $value
              fi
          fi
       done
    fi
}
 
start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
    make_dirs
    echo -n $"Starting $prog: "
    daemon $nginx -c $NGINX_CONF_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}
 
stop() {
    echo -n $"Stopping $prog: "
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}
 
restart() {
    configtest || return $?
    stop
    sleep 1
    start
}
 
reload() {
    configtest || return $?
    echo -n $"Reloading $prog: "
    killproc $nginx -HUP
    RETVAL=$?
    echo
}
 
force_reload() {
    restart
}
 
configtest() {
  $nginx -t -c $NGINX_CONF_FILE
}
 
rh_status() {
    status $prog
}
 
rh_status_q() {
    rh_status >/dev/null 2>&1
}
 
case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart|configtest)
        $1
        ;;
    reload)
        rh_status_q || exit 7
        $1
        ;;
    force-reload)
        force_reload
        ;;
    status)
        rh_status
        ;;
    condrestart|try-restart)
        rh_status_q || exit 0
            ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}"
        exit 2
esac

修改权限
chmod +x /etc/init.d/nginx

将nginx服务加入chkconfig管理列表：
chkconfig --add /etc/init.d/nginx

加完这个之后，就可以使用service对nginx进行启动，重启等操作。
service nginx start
service nginx stop
service nginx restart

修改完配置文件需要重新加载一下自启
[root@hecs-94308 sbin]# systemctl daemon-reload
[root@hecs-94308 sbin]# systemctl restart nginx
[root@hecs-94308 sbin]# systemctl status nginx


#======== tomcat
wget https://archive.apache.org/dist/tomcat/tomcat-8/v8.5.37/bin/apache-tomcat-8.5.37.tar.gz

解压到usr/local/

启动tomcat
/tomcat/bin/startup.sh

把war项目放在
/usr/local/tomcat/webapps

在conf中的server.xml中的最后一个<Host></Host>中添加
<Context path="" docBase="/opt/app/tomcat/webapps/lcop-manager-web"  debug="0" reloadable="true"></Context>

tomcat配置登录用户名和密码
①，当tomcat安装完成之后，访问http://ip:8080即可以看到tomcat发布的网站，这时候需要管理我们发布网站，就需要登录tomcat，那么在centos中如何设置登录名和密码。
②，在tomcat安装包下面的conf文件下含有一个tomcat-user.xml文件，修改此文件即可，命令为：vim tomcat-users.xml
③，在打开的xml配置文件最后面写入下面这段配置文件之后保存并且退出即可

#========= rabbitmq

安装erlang 
wget https://github.com/rabbitmq/erlang-rpm/releases/download/v21.3.8.15/erlang-21.3.8.15-1.el8.x86_64.rpm

rpm -Uvh erlang-21.3.8.15-1.el8.x86_64.rpm

安装socat
yum install -y socat

安装rabbitmq
wget https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.7.26/rabbitmq-server-3.7.26-1.el8.noarch.rpm
 
rpm -Uvh rabbitmq-server-3.7.26-1.el8.noarch.rpm

启动 
systemctl start rabbitmq-server
systemctl status rabbitmq-server
加入开机自启
systemctl enable rabbitmq-server

配置文件位置
/usr/lib/rabbitmq/lib/rabbitmq_server-3.7.26/ebin/rabbit.app

访问不了15672
cd /usr/lib/rabbitmq/bin/
 ./rabbitmq-plugins enable rabbitmq_management
 重新启动rabbitmq