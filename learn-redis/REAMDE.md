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

- 