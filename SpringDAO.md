## Spring DAO
### 1.原始版本的DAO
### 2.使用Spring的JDBC
### 3.JdbcTemplate查询
### 4.事务概述
### 5.声明式事务

### 1.原始版本的DAO
##### 原生的JDBC：需要手动去数据库的驱动从而拿到对应的连接
```
		try {
			String sql = "insert into t_dept(deptName) values('test');";
			Connection con = null;
			Statement stmt = null;
			Class.forName("com.mysql.jdbc.Driver");
			// 连接对象
			con = DriverManager.getConnection("jdbc:mysql:///hib_demo", "root", "root");
			// 执行命令对象
			stmt =  con.createStatement();
			// 执行
			stmt.execute(sql);
			
			// 关闭
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
```
### 2.使用spring的JDBC
##### 首先我们需要引入2的jar包
- spring-jdbc-3.2.5.RELEASE.jar
- spring-tx-3.2.5.RELEASE.jar
##### Spring JDBC对C3P0数据库连接池是有很好的支持的。因此我们直接可以使用Spring的依赖注入，在配置文件中配置dataSource就行了！
##### Spring来提供了JdbcTemplate这么一个类给我们使用！它封装了DataSource，也就是说我们可以在Dao中使用JdbcTemplate就行了。
```
 <!-- jdbc配置开始  -->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!-- 配置c3p0数据库连接池 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <!-- c3p0连接池的私有属性 -->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!-- 关闭连接后不自动commit -->
        <property name="autoCommitOnClose" value="false"/>
        <!-- 获取连接超时时间 -->
        <property name="checkoutTimeout" value="10000"/>
        <!-- 当获取连接失败重试次数 -->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>
    
    <!-- springbean jdbc -->
    <!-- 2. 创建JdbcTemplate对象 -->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
```
### 3.JdbcTemplate查询
```
@Component
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void select() {
        String sql = "select * from book";
        List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                book.setBook_id(resultSet.getString("book_id"));
                book.setNumber(resultSet.getString("number"));
                return book;
            }
        });

        System.out.println(books);
    }

    public void update() {
        String sql = "insert into book(name,number) values('测试课本',123)";
        jdbcTemplate.update(sql);
    }
}
```
### 4.事务概述
##### 事务控制都是在service层做的，service层是业务逻辑层，service的方法一旦执行成功，那么说明该功能没有出错。
##### 事务控制分为两种：
- 编程式事务控制
- 声明式事务控制
### 5.声明式事务
##### Spring提供对事务的控制管理就叫做声明式事务控制
##### Spring给我们提供了事务的管理器类，事务管理器类又分为两种，因为JDBC的事务和Hibernate的事务是不一样的。
##### Spring声明式事务管理器类：
- Jdbc技术：DataSourceTransactionManager
- Hibernate技术：HibernateTransactionManager
##### jar包引入
- AOP相关的jar包【因为Spring的声明式事务控制是基于AOP的，那么就需要引入AOP的jar包。】
- 引入tx名称空间
- 引入AOP名称空间
- 引入jdbcjar包【jdbc.jar包和tx.jar包】
##### 配置事务的管理器类
    <bean id="txManage" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--引用数据库连接池-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

**配置事务管理器类如何管理事务**

```
 <!--2.配置如何管理事务-->
    <tx:advice id="txAdvice" transaction-manager="txManage">

        <!--配置事务的属性-->
        <tx:attributes>
            <!--所有的方法，并不是只读-->
            <tx:method name="*" read-only="false"/>
        </tx:attributes>
    </tx:advice>
  <!--3.配置拦截哪些方法+事务的属性-->
    <aop:config>
        <aop:pointcut id="pt" expression="execution(* com.wind.spring.springjdbc.*.*(..) )"/>
        <aop:advisor advice-ref="txAdvice" pointcut-ref="pt"></aop:advisor>
    </aop:config>
```
**使用注解的方法实现事务控制**
##### 第一步和XML的是一样的，必须配置事务管理器类：
```
<!--1.配置事务的管理器类:JDBC-->
    <bean id="txManage" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">

        <!--引用数据库连接池-->
        <property name="dataSource" ref="dataSource"/>
    </bean>
    
    <!--开启以注解的方式实现事务控制-->
    <tx:annotation-driven transaction-manager="txManage"/>
    
```

