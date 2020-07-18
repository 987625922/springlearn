## MYSQL

- 连接

  ```
  mysql -u root -p
  ```

- 选择数据库

  ```
  USE 数据库名称;
  ```

- 展现mysql里的库名

  ```
  SHOW DATABASES;
  ```

- 展现数据库里的表(在使用这个数据库之后)

  ```
  SHOW TABLES;
  ```

- 查看表中各个参数的详情

  ```
   SHOW COLUMNS FROM 表名;
  ```

- 搜索只显示不同结果

  ```
  DELECT DISTINCT 表列名 FROM 表名;
  ```

- 限制搜索结果

  ```
  SELECT * FROM 表名  LIMIT 5;(LIMIT 4,5)
  ```

- 排序数据

  ```
  SELECT * FROM 表名 ORDER BY 列名,列名....;
  ```

- 降序排序

  ```
  SELECT * FROM 表名 ORDER BY 列名 DESC;
  ```

- WHERE子句

  ```
  SELECT * FROM 表名 WHERE 列名 = 值;
  ```

  子句操作符

  | 操作符      | 说明              |
  | ----------- | ----------------- |
  | <>          | 不等于            |
  | !=          | 不等于            |
  | <=          | 小于等于          |
  | BETWEEN AND | 在指定的2个值之间 |

- 多个WHERE条件

  ```
  SELECT * FROM 表名 WHERE 列名 = 值 AND 列名 = 值
  ```

- 匹配一个WHERE条件

  ```
  SELECT * FROM 表名 WHERE 列名 = 值 OR 列名 = 值
  ```

  OR和AND在一起时，AND优先级高

- WHERE范围匹配（搜索出括号里的值）

  ```
  SELECT * FROM 表名 WHERE 列名 IN (值,值)
  ```

- 否定匹配 NOT(搜索出不等于括号里的值的列)

  ```
  SELECT * FROM 表名 WHERE 列名 NOT IN (值,值)
  ```

- LIKE操作符

  ```
  SELECT * FROM 表名 WHERE 列名 LIKE '_匹配的值%';
  ```

- 分组

  ```
  SELECT * FROM 表名 GROUP BY 列名;
  ```

- 过滤分组

  ```
  SELECT * FROM 表名 GROUP BY 列名 HAVING 列名 > 值;
  ```

- 子查询

  ```
  SELECT * FROM customers WHERE cust_id IN (SELECT cust_id FROM order WHERE order_num IN (SELECT order_num FROM orderitems WHERE prod_id = 'TNT2'));
  ```

- 内联结

  ```
  SELECT * FROM 表名1 INNER JOIN 表名2 ON 表名1.列名 = 表名2.列名;
  ```

  ```
  SELECT * FROM vendors INNER JOIN products ON vendors.vend_id = products.vend_id;
  ```

- 左右联结

  LEFT OUTER JOIN:左表中的行就算不匹配也会显示

  RIGHT OUTER JOIN:右表中的行就算不匹配也会显示

  INNER JOIN:只有匹配的行了才会显示
  
- 组合查询(显示2个搜索的结果)

  ```
  SELECT * FROM 表名 UNION SELECT * FROM 表名;
  ```

- 插入数据

  ```
  INSERT INTO 表名 VALUES(值...),(值...);
  ```

- 更新数据

  ```
  UPDATE 表名 SET 列名 = 值,列名 = 值 WHERE 列名 = 值;
  ```

- 删除数据

  ```
  DELECT FROM 表名 WHERE 列名 = 值;
  ```

- 创建表

  ```
  CREATE TABLE user(
  id int NOT NULL AUTO_INCREMENT,
  name char(50) NOT NULL DEFAULT '插入为空时默认的名字',
  PRIMARY KEY(id)
  )ENGINE=InnoDB;
  ```

- 表添加一个列名

  ```
  ALTER TABLE 表名 ADD 列名 CHAR(20)
  ```

- 表删除一个列名

  ```
  ALTER TABLE 表名 DROP COLUMN 列名;
  ```

- 删除表

  ```
  DROP TABLE 表名;
  ```

- 重命名表

  ```
  RENAME TABLE 表名 TO 要修改成为的表名;
  ```

  