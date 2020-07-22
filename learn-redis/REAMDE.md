### 基本命令

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

  - 

