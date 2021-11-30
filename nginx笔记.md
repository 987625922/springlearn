### 基本命令
```$xslt
配置文件启动
./sbin/nginx -c /conf/nginx.conf

指定nginx程序目录启动
./sbin/nginx -p /usr/local/nginx

停止:第二种更加优雅
./sbin/nginx -s stop
./sbin/nginx -s quit

重载配置文件
./sbin/nginx -s reload

```

### 代理的相关参数
```
proxy_pass            #代理服务
proxy_redirect off;   #是否允许重定向
proxy_set_header Host $host;   #转发时传header参数
proxy_set_header X-Forwarded-For $remote_addr;  #设置请求的ip地址
proxy_connect_timeout 90;  #连接代理服务超时时间
proxy_send_timeout 90;  #请求发送最大时间
proxy_read_timeout 90;  #读取最大时间
缓存相关
proxy_buffer_size 4k;
proxy_buffers 4 32k;
proxy_busy_buffers_size 64k;
proxy_temp_file_write_size 64k;

```

### 设置代理
```$xslt
location /javayz/ {
    proxy_pass http://127.0.0.1:8080;
}
```
设置完之后当访问 /javayz时就会代理到本机的8080端口上。
按照上面的配置会转发到：
http://127.0.0.1:8080/javayz/index.html。
```
location /javayz/ {
    proxy_pass http://127.0.0.1:8080/;
}
```
转发后的地址变成：http://127.0.0.1:8080/index.html

### nginx负载均衡
```
#配置集群，这段代码写在http块中
upstream backend {
    server 127.0.0.1:8081;
    server 127.0.0.1:8082;
}
#配置在server块中
location / {
    proxy_pass http://backend/;
}
```
upstream的相关参数如下：
```
server 服务ip:端口
weight 权重
max_fails  最多失败连接的次数，超过就认为主机挂掉了
fail_timeout  重新连接的时间
backup  备用服务
max_conns  允许的最大连接数
slow_start  节点恢复后，等待多少秒后再加入
```
### Nginx负载均衡算法
权重
```
upstream backend {
    server 127.0.0.1:8081 weight=2;
    server 127.0.0.1:8082 weight=1;
}
```
ip_hash算法
对于访问的ip，他会做一次hash运算，并对当前的负载应用数量做一次取余运算，这种算法能保证同一个ip访问的是同一台应用服务器。
```
upstream backend {
    ip_hash;
    server 127.0.0.1:8081;
    server 127.0.0.1:8082;
}
```
url_hash算法
对于请求的url进行hash运算，这种算法能保证同一个url访问的是同一台应用服务器。
```
upstream backend {
    url_hash;
    server 127.0.0.1:8081;
    server 127.0.0.1:8082;
}
```
least_conn算法：将请求分发到连接数最少的节点上。

least_time算法：将请求分配到响应最快的节点上。

### Nginx缓存
如果每次请求都需要反向代理给应用服务器，那对带宽和性能的压力是很大的，Nginx中有对缓存的支持，它可以将那些变化不大的前端静态页面加载到缓存中，增强整体的性能。
#### 在http元素下添加缓存声明
该语句放在http元素下
```
proxy_cache_path /www/javayz/cache levels=1:2 keys_zone=cache_javayz:500m inactive=20d max_size=1g;

#proxy_cache_path  缓存存放的路径 
#levels  缓存层级及目录的位数，1:2表示两级目录，第一级目录用1位16进制表示，第二级目录用2位16进制表示
#keys_zone 缓存区内存大小
#inactive 有效期，如果缓存有效期内未使用，则删除
#max_size 存储缓存的硬盘大小
```
####  在location中设定缓存策略
该语句放在location元素中
```
#指定缓存区，就是上面设置的key_zone
proxy_cache cache_javayz;

#缓存的key，这里用请求的全路径md5做为key
proxy_cache_key $host$uri$is_args$args;

#对不通的http状态码设置不同的缓存时间,下面的配置表示200时才进行缓存，缓存时间12小时
proxy_cache_valid 200 12h;
```
