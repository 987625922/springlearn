## Spring DAO
### 1.事务概述
### 2.声明式事务

### 3.事务的传播行为

### 1.事务概述
##### 事务控制都是在service层做的，service层是业务逻辑层，service的方法一旦执行成功，那么说明该功能没有出错。
##### 事务控制分为两种：
- 编程式事务控制
- 声明式事务控制
### 2.声明式事务
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
        <aop:pointcut id="pt" expression="execution(* com.wind.spring.databases.springjdbc.*.*(..) )"/>
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

### 3.事务的传播行为

-  Spring中七种事务传播行为

  | PROPAGATION_REQUIRED      | 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。 |
  | ------------------------- | ------------------------------------------------------------ |
  | PROPAGATION_SUPPORTS      | 支持当前事务，如果当前没有事务，就以非事务方式执行           |
  | PROPAGATION_MANDATORY     | 使用当前的事务，如果当前没有事务，就抛出异常。               |
  | PROPAGATION_REQUIRES_NEW  | 新建事务，如果当前存在事务，把当前事务挂起。                 |
  | PROPAGATION_NOT_SUPPORTED | 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。   |
  | PROPAGATION_NEVER         | 以非事务方式执行，如果当前存在事务，则抛出异常。             |
  | PROPAGATION_NESTED        | 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。 |

  **NESTED和REQUIRED修饰的内部方法都属于外围方法事务，如果外围方法抛出异常，这两种方法的事务都会被回滚。但是REQUIRED是加入外围方法事务，所以和外围事务同属于一个事务，一旦REQUIRED事务抛出异常被回滚，外围方法事务也将被回滚。而NESTED是外围方法的子事务，有单独的保存点，所以NESTED方法抛出异常被回滚，不会影响到外围方法的事务。**

- spring事务的四种隔离级别

  | 事务隔离级别               | 脏读 | 不可重复读 | 幻读 |
  | -------------------------- | ---- | ---------- | ---- |
  | 读未提交(Read-Uncommitted) | 是   | 是         | 是   |
  | 不可重复读(Read-Committed) | 否   | 是         | 是   |
  | 可重复读(Repeatable-Read)  | 否   | 否         | 是   |
  | 串行化(Serializable)       | 否   | 否         | 否   |

  