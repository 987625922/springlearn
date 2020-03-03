1. ## Hibernate 映射类型
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

2. ## Hibernate O/R 映射


- 集合映射
如果一个实例或者类中有特定变量的值的集合，那么我们可以应用 Java 中的任何的可用的接口来映射这些值。Hibernate 可以保存 **java.util.Map, java.util.Set, java.util.SortedMap, java.util.SortedSet, java.util.List** 和其它持续的实例或者值的任何**数组**的实例。

| **集合类型**              | **映射和描述**                                               |
| :------------------------ | :----------------------------------------------------------- |
| **java.util.Set**         | 它和 \<set> 元素匹配并且用 java.util.HashSet 初始化。        |
| **java.util.SortedSet**   | 它和 \<set> 元素匹配并且用 java.util.TreeSet 初始化。**sort** 属性可以设置成比较器或者自然排序。 |
| **java.util.List**        | 它和 \<list> 元素匹配并且用 java.util.ArrayList 初始化。     |
| **java.util.Collection**  | 它和 \<bag> 或者 \<ibag> 元素匹配以及用 java.util.ArrayList 初始化。 |
| **java.util.Map**         | 它和 \<map> 元素匹配并且用 java.util.HashMap 初始化。        |
| **java.util.SortedMap**") | 它和 \<map> 元素匹配并且用 java.util.TreeMap 初始化。**sort** 属性可以设置成比较器或者 自然排序。 |

对于 Java 的原始数值 Hibernate 采用``支持数组，对于 Java 的其它数值 Hibernate 采用``支持数组。然而它们很少被应用，因此我也就不在本指导中讨论它们。

如果你想要映射一个用户定义的集合接口而这个接口不是 Hibernate 直接支持的话，那么你需要告诉 Hibernate 你定义的这个集合的语法，这个很难操作而且不推荐使用。

- 关联映射

实体类之间的关联映射以及表之间的关系是 ORM 的灵魂之处。对象间的关系的子集可以用下列四种方式解释。关联映射可以是单向的也可以是双向的。

| **映射类型**     | **描述**                      |
| :--------------- | :---------------------------- |
| **Many-to-One**  | 使用 Hibernate 映射多对一关系 |
| **One-to-One**   | 使用 Hibernate 映射一对一关系 |
| **One-to-Many**  | 使用 Hibernate 映射一对多关系 |
| **Many-to-Many** | 使用 Hibernate 映射多对多关系 |

3. Hibernate 注释

- @NonNull : 注解在参数上, 如果该类参数为 null , 就会报出异常,  throw new NullPointException(参数名)

- @Cleanup : 注释在引用变量前, 自动回收资源 默认调用 close() 方法

- @Getter/@Setter : 注解在类上, 为类提供读写属性

- @Getter(lazy=true) :

- @ToString : 注解在类上, 为类提供 toString() 方法

- @EqualsAndHashCode : 注解在类上, 为类提供 equals() 和 hashCode() 方法
 
- @NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor : 注解在类上, 为类提供无参,有指定必须参数, 全参构造函数

- 构造函数
- @AllArgsConstructor
会生成一个包含所有变量，同时如果变量使用了NotNull annotation ， 会进行是否为空的校验， 全部参数的构造函数的自动生成，该注解的作用域也是只有在实体类上，参数的顺序与属性定义的顺序一致。

- @NoArgsConstructor
无参构造函数
@RequiredArgsConstructor
会生成一个包含常量（final），和标识了@NotNull的变量 的构造方法。

- @Data : 注解在类上, 为类提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法

- @Value :

- @Builder : 注解在类上, 为类提供一个内部的 Builder

- @SneakThrows :

- @Synchronized : 注解在方法上, 为方法提供同步锁

- @Log :

- @Log4j : 注解在类上, 为类提供一个属性名为 log 的 log4j 的日志对象

- @Slf4j : 注解在类上, 为类提供一个属性名为 log 的 log4j 的日志对象
