1. ## Hibernate核心API

    - Configuration 类
        启动hibernate程序，加载hibernate.cfg.xml配置文件
      
      - SeesionFactory 接口
    
        加载连接数据，扩展参数，映射信息，通过这些映射信息帮助我们创建Session
    
      - Session 接口
    
        操作数据库
    
        常用方法：
    
        - save() 保存对象
      - update() 更新对象
        - delete() 删除对象
      - get() 查询对象
        - saveOrUpdate() 根据Id判断是调用save或者update方法,该方法更倾向于不缺定是插入还是更新，而且你不需要得到他的主键。
      - clear() 调用clear方法会清除session已经存在的所有缓存的实例
        - evict(obj) 会把指定的缓冲对象进行清除
      - flush() flush默认是在Transaction.commit()时被调用,底层持久化存储和内存中的持久化状态的同步过程
      
    - Transaction 接口
      
      begin() 开启事务
      
      commit() 提交事务
      
      rollback() 回滚事务
      
    - Query 接口
      
      执行HQL查询
      
    - Criteria
      
        执行基于对象的查询(QBC查询)


2. ## 核心配置

   - 普通属性
   
     ```
     <hibernate-mapping> 
                 package 用于配置PO类所在包
                     例如： package="com.itheima.d_hbm"
             <class> 配置 PO类 和 表 之间对应关系
                 name：PO类全限定类名
                     例如：name="com.itheima.d_hbm.Person"
                     如果配置 package，name的取值可以是简单类名 name="Person"
                 table : 数据库对应的表名
                 dynamic-insert="false" 是否支持动态生成insert语句
                 dynamic-update="false" 是否支持动态生成update语句
                     如果设置true，hibernate底层将判断提供数据是否为null，如果为null，insert或update语句将没有此项。
             普通字段
                 <property>
                     name : PO类的属性
                     column : 表中的列名，默认name的值相同
                     type:表中列的类型。默认hibernate自己通过getter获得类型，一般情况不用设置
                         取值1： hibernate类型
                             string 字符串
                             integer 整形
                         取值2： java类型 （全限定类名）
                             java.lang.String 字符串
                         取值3：数据库类型
                             varchar(长度) 字符串
                             int 整形
                             <property name="birthday">
                                  <column name="birthday" sql-type="datetime"></column>
                              </property>
                              javabean 一般使用类型 java.util.Date
                              jdbc规范提供3中
                                  java类型                mysql类型
                                  java.sql.Date        date
                                  java.sql.time        time
                                  java.sql.timestamp    timestamp
                                  null                datetime
     
                                  以上三个类型都是java.util.Date子类
     
                     length : 列的长度。默认值：255
                     not-null : 是否为null
                     unique : 是否唯一
                     access：设置映射使用PO类属性或字段
                         property : 使用PO类属性，必须提供setter、getter方法
                         field : 使用PO类字段，一般很少使用。
                     insert 生成insert语句时，是否使用当前字段。
                     update 生成update语句时，是否使用当前字段。
                         默认情况：hibernate生成insert或update语句，使用配置文件所有项
             注意：配置文件如果使用关键字，列名必须使用重音符
     ```
   
   - 主键
   
     ```
     <id>配置主键
     name:属性名称
     access="" 设置使用属性还是字段
     column=""  表的列名
     length=""  长度
     type="" 类型
     <generator> class属性用于设置主键生成策略
     1.increment 由hibernate自己维护自动增长
         底层通过先查询max值，再+1策略
         不建议使用，存在线程并发问题
     2.identity hibernate底层采用数据库本身自动增长列
         例如：mysql auto_increment
     3.sequence hibernate底层采用数据库序列
         例如：oracle 提供序列
     4.hilo 
     
         </generator>
     5.native 根据底层数据库的能力选择 identity、sequence 或者 hilo 中的一个。【】
     ##以上策略使用整形，long, short 或者 int 类型
     6.uuid 采用字符串唯一值【】
     ##以上策略 代理主键，有hibernate维护。
     7.assigned 自然主键，由程序自己维护。【】
     ```

3. ## Hibernate 一级缓存

   - #### 三种状态

     - 瞬时态：transient，session没有缓存对象，数据库也没有对应记录。(new 出来的对象)
       对象的id：没有值
     - 持久态：persistent，session缓存对象，数据库最终会有记录。（在事务内，事务没有提交）
       对象的id：有值
     - 脱管态：detached，session没有缓存对象，数据库有记录。（事务已经提交后）
       对象的id：有值
     
   - ### 一级缓存

     一级缓存：又称为session级别的缓存。当获得一次会话（session），hibernate在session中创建多个集合（map），用于存放操作数据（PO对象），为程序优化服务，如果之后需要相应的数据，hibernate优先从session缓存中获取，如果有就使用；如果没有再查询数据库。当session关闭时，一级缓存销毁。

   - ### 快照

     与一级缓存一样的存放位置，对一级缓存数据备份。保证数据库的数据与 一级缓存的数据必须一致。如果一级缓存修改了，在执行commit提交时，将自动刷新一级缓存，执行update语句，将一级缓存的数据更新到数据库。

     当缓存和数据库数据不一样且在提交之前，可以调用 refresh 强制刷新缓存。

   - ### 级联

     cascade 表示指定级联操作的类型。

     - save-update : 增加或更新 A 时，自动增加或更新 B。
     - delete : 删除 A 时，自动删除 B
     - all : 上面两项效果叠加
     - delete-orphan (孤儿删除) : 删除所有和当前对象解除关联关系的对象
     - all-delete-orphan : all + delete-orphan 效果叠加

   - 一级缓存的数据操作

     - session.evict(Object)
     - session.clear()  //清除session里面的一级缓存

   

4. ## 抓取策略

   ### 检索方式

   - 立即检索：立即查询，在执行查询语句时，立即查询所有的数据。
   - 延迟检索：延迟查询，在执行查询语句之后，在需要时在查询。（懒加载）

   ### 检索策略

   - 类级别检索：当前的类的属性获取是否需要延迟。
   - 关联级别的检索：当前类 关联 另一个类是否需要延迟。

   ### 类级别检索

   - get：立即检索。get方法一执行，立即查询所有字段的数据。

   - load：延迟检索。默认情况，load方法执行后，如果只使用OID的值不进行查询，如果要使用其他属性值将查询。可以配置是否延迟检索：

     ```
       <class  lazy="true | false">
       lazy 默认值true，表示延迟检索，如果设置false表示立即检索。
     ```

   ### 关联级别检索

   容器<set> 提供两个属性：fetch、lazy，用于控制关联检索。

   - fetch：确定使用sql格式
     - join：底层使用迫切左外连接
     - select：使用多个select语句（默认值）
     - subselect：使用子查询
   - lazy：关联对象是否延迟。
     - false：立即
     - true：延迟（默认值）
     - extra：极其懒惰，调用 size 时，sql 查询 count。（用于只需要获取个数的时候）

   ### 批量查询

   一次加载多行数据，用于减少 sql 语句数量
   `<set batch-size="5">`

   比如： 当客户关联查询订单时，默认给每一个客户生产一个select语句查询订单。开启批量查询后，使用in语句减少查询订单语句个数。

   ```
   默认：select * from t_order where customer_id = ?
   批量：select * from t_order where customer_id in (?,?,?,?)
   ```

5. 事务
   ##### 隔离级别

   - read uncommittd，读未提交。存在3个问题。

   - read committed，读已提交。解决：脏读。存在2个问题。

   - repeatable read ，可重复读。解决：脏读、不可重复读。存在1个问题。

   - serializable，串行化。单事务。没有问题。

     ```
     hibernate 中配置
     <property name="hibernate.connection.isolation">4</property>
     对照上面的分别是 1 2 4 8，0表示没有事务级别
     ```

- 锁

  - 悲观锁
    采用数据库锁机制。丢失更新肯定会发生。

  - 读锁：共享锁。
      select .... from  ... lock in share mode;

  - 写锁：排他锁。（独占）

    select ... from  ....  for update

  - 乐观锁

    在表中提供一个字段（版本字段），用于标识记录。如果版本不一致，不允许操作。丢失更新肯定不会发

    ```
    Hibernate 中使用
    在PO对象（javabean）提供字段，表示版本字段。
     private Integer version;
    在配置文件中增加 version
     <class ...>
         <version name="version" />
    ```

6. ## Hibernate 映射类型
- 原始类型

  
| 映射类型    | Java 类型                    | ANSI SQL 类型        |
| :---------- | :--------------------------- | :------------------- |
| integer     | int 或 java.lang.Integer     | INTEGER              |
| long        | long 或 java.lang.Long       | BIGINT               |
| short       | short 或 java.lang.Short     | SMALLINT             |
| float       | float 或 java.lang.Float     | FLOAT                |
| double      | double 或 java.lang.Double   | DOUBLE               |
| big_decimal | java.math.BigDecimal         | NUMERIC              |
| character   | java.lang.String             | CHAR(1)              |
| string      | java.lang.String             | VARCHAR              |
| byte        | byte 或 java.lang.Byte       | TINYINT              |
| boolean     | boolean 或 java.lang.Boolean | BIT                  |
| yes/no      | boolean 或 java.lang.Boolean | CHAR(1) ('Y' or 'N') |
| true/false  | boolean 或 java.lang.Boolean | CHAR(1) ('T' or 'F') |

- 日期和时间类型

| 映射类型      | Java 类型                            | ANSI SQL 类型 |
| :------------ | :----------------------------------- | :------------ |
| date          | java.util.Date 或 java.sql.Date      | DATE          |
| time          | java.util.Date 或 java.sql.Time      | TIME          |
| timestamp     | java.util.Date 或 java.sql.Timestamp | TIMESTAMP     |
| calendar      | java.util.Calendar                   | TIMESTAMP     |
| calendar_date | java.util.Calendar                   | DATE          |

- 二进制和大型数据对象

| 映射类型     | Java 类型                                           | ANSI SQL 类型       |
| :----------- | :-------------------------------------------------- | :------------------ |
| binary       | byte[]                                              | VARBINARY (or BLOB) |
| text         | java.lang.String                                    | CLOB                |
| serializable | any Java class that implements java.io.Serializable | VARBINARY (or BLOB) |
| clob         | java.sql.Clob                                       | CLOB                |
| blob         | java.sql.Blob                                       | BLOB                |

- JDK 相关类型

| 映射类型 | Java 类型          | ANSI SQL 类型 |
| :------- | :----------------- | :------------ |
| class    | java.lang.Class    | VARCHAR       |
| locale   | java.util.Locale   | VARCHAR       |
| timezone | java.util.TimeZone | VARCHAR       |
| currency | java.util.Currency | VARCHAR       |

7. 解决json输出多对多时循环（不好的方法，只是写笔记记住，后续解决）
```
 1.一方要在注解上这样写
 @ManyToMany(mappedBy = "permissions",fetch = FetchType.EAGER)
     @JsonBackReference
 2.并且要重写toString，注意多对多的参数不能出现在toString中
 3.另一方注解
 @ManyToMany(targetEntity = Permission.class
             ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinTable(name = "t_role_permission",
             joinColumns = @JoinColumn(name = "role_id"),
             inverseJoinColumns = @JoinColumn(name = "permission_id"))
 4，：已知问题：
    1.只能一方导入数据是才有多对多的依赖，从另一方就不行
```