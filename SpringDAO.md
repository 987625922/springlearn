## Spring DAO
### 1.原始版本的DAO
### 2.使用Spring的JDBC

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
