## 基本命令

- ```
  redis-cli 在文件夹下cmd输入 远程  redis-cli -h host -p port -a password 例如 redis-cli -h 127.0.0.1 -p 6379 -a "mypass"
  ```

- ```
  ping redis是否运行正常
  ```
  
- ```
  info 查看redis信息
  ```

- ```
  dbsize 查看当前key数
  ```

- ```
  flushall 删除所有数据
  ```

- ```
  key * 查看所有key
  *:表示0-多个字符
  ?:表示单个字符 例如wo?d 匹配word wood
  []:表示选择[]内的字符 例如 wo[or]d 匹配 word,wood，不匹配 wold
  ```

- ```
  exists key名字 判断某个key是否存在 存在就返回1
  ```

- ```
  expire key名字 多少秒 成功返回1
  ```

- ```
  ttl key名字 返回-1表示永不过期，-2表示key不存在，数字表示key的还有多少秒过期
  ```

- ```
  type key名字 不存在就返回none
  ```

- ```
  del key名字 成功就返回1
  ```

## 5种数据类型

- String

  - 常用命令

    - ```
      set key名字
      ```

      设置一个string类型的值

    - ```
      incr key名字 
      ```

      设置一个string类型的值加一

    - ```
      decr key名字
      ```

      设置一个string类型的值减一

    - ```
      incrby key名字 数值
      ```

      设置一个string加数值

    - ```
      decrby key名字 数值
      ```

      设置一个string减数值

    - ```
      append key名字 字符串
      ```

      向一个string追加一个字符串

- hash类型

  - ```
    hset user name zhangsan
    hset user age 18
    hset user sex 1
    ```

    设置一个hash的集合

  - ```
    hget user name
    ```

    获取hash集合中一个键的值

  - ```
    hmset user name zhangsan age 18 sex 1
    ```

    设置一个集合

  - ```
    hgetall user
    ```

    获取一个hash集合里所有的值

  - ```
    hdel 集合名 集合里面的键名
    ```

    删除hash集合里面的某个键

  - ```
    hkeys user
    ```

    获取hash集合里所有的key

- list类型

  - ```
    lpush mylist a b c
    ```

    向redis中插入名为mylist的键，值为c b a，如果再push一个就会放在前面

  - ```
    rpush mylist c
    ```

    向redis中插入名为mylist的键，值放在最后

  - ```
    lrange mylist 0 5
    ```

    从左边开始输出5个

  - ```
    lpop mylist
    ```

    从list开始删除一个值

  - ```
    rpop mylist
    ```

    从list最后删除一个值

  - ```
    lindex mylist 1
    ```

    获取list中下标的值

  - ```
    llen mylist
    ```

    获取list的长度

  - ```
    lrem mylist 2 html
    ```

    删除list中的2个html，如果2为0就表示删除所有的html

  - ```
    ltrim mylist 0 2
    ```

    除了list中的0，1，2的下标，其他的都删除

  - ```
    linsert mylist before html java
    ```

    在redis中的html之前插入一个java

- set类型

  - ```
    sadd countrys CN EN
    ```

    向名为countrys的set中插入CN，EN，成功返回 0，countrys为这个set的名字

  - ```
    smembers countrys
    ```

    打印所有的值

  - ```
    sismember countrys EN
    ```

    判断这个set集合里是否有这个值，有就返回 1

  - ```
    scard countrys
    ```

    统计set集合里面值的数量

  - ```
    srem countrys EN
    ```

    把EN从set集合删除

## 发布订阅

- ```
  SUBSCRIBE cctv5
  ```

  订阅一个频道

- ```
  publish cctv5 "hello"
  ```

  发布一个消息

- ### java实现

  ```
  订阅者：继承JedisPubSub类，onMessage方法会回调发布者发布的消息
  订阅者：Jedis.publish 来发布消息
  learn-redis模块中有实例
  ```

## 事务

- ### 事务的使用

  ```
  127.0.1:6379> multi 开启事务，其后命令加入命令队列，暂时不执行
  127.0.1:6379> sadd countrys CN 添加一个值
  127.0.1:6379> sadd countrys EN 添加一个值
  127.0.1:6379>exec 提交
  ```

  ```
  127.0.1:6379> discard 放弃事务，事务开启后所有的操作都不提交
  ```

- ### WATCH机制

  ```
  127.0.1:6379> set age 11 设置age为11
  127.0.1:6379> watch age 监视age的值
  127.0.1:6379> multi 开启事务
  127.0.1:6379> incr age age加一
  127.0.1:6379> exec 提交事务
  如果age在事务之外被其他线程修改了，事务提交就会失败，如果只有事务中在修改age，事务提交成功
  ```

## 持久化

- ### RDB（默认启用）

  #### 按指定的时间间隔把数据写到本地，数据恢复时将快照文件直接读到内存

  #### 在redis.conf中可以配置RDB的参数

- ### AOF

  ##### redis每接收到一条修改数据的命令，就将命令写在一个AOF文件中，当redis重启时，就通过执行AOF文件中的所有命令来恢复数据。

  - ##### 配置（redis.conf）

    - appendonly 是否开启 默认是no，改为yes开启
    - dir 指定RDB和AOF文件存放目录，默认是./
    - appendfsync 配置向aof文件写命令数据的策略：
      - no 不主动进行同步操作，而是每隔30秒一次，快但是不是很安全
      - always 每次写入都进行同步
      - everysec 每秒进行同步，默认项

    ### 同时使用这2种模式时，redis会优先默认加载aof文件

## 主从复制

##### redis主从配置，只要在redis.conf文件中配置指向的宿主机器。

##### 修改conf文件

```
slaveof 102.168.1.155 6379
```

- ### 主从复制的容灾处理

  - ###### 当Master服务出现故障，手动将slave中的一个提升为master，剩下的slave挂至新的maseter上（手动）

  - ###### 使用哨兵模式（自动）

## 哨兵模式（自动的容灾处理）

#### 修改sentine.conf文件

```
sentinel monitor mymaster 192.168.1.1(masterip地址) 6379(master端口)
```

#### 启动哨兵

```
./bin/reids-sentinel sentinel.conf
```
