### 1.spring的资源表达式

| 地址前缀    | 示例                                                         | 对应资源类型                                                 |
| ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| classpath： | classpath:jdbc.properties                                    | 即项目中的resources路径<context:property-placeholder location="classpath:jdbc.properties"/>，没有特殊需求不要用classpath*，会同时加载jar包中同名的资源文件 |
| file:       | <context:property-placeholder location="file:/Users/mac/Desktop/IDEA Project/dubbodemo/dubbodemo-user-service/lc.properties" | 加载的是系统的路径，file我们可以将配置文件放在项目外边，这个标签也完美的解决文章最前边提到的运维人员更改jar包配置的问题。当然war包也可以这样配置。 |

### 2.DispatcherServlet

SpringMVC的核心就是DispatcherServlet，DispatcherServlet实质也是一个HttpServlet。DispatcherSevlet负责将请求分发，所有的请求都有经过它来统一分发。

1. Tomcat 启动，对 DispatcherServlet 进行实例化，然后调用它的 init() 方法进行初始化，在这个初始化过程中完成了：

2. 对 web.xml 中初始化参数的加载；建立 WebApplicationContext (SpringMVC的IOC容器)；进行组件的初始化；

3. 客户端发出请求，由 Tomcat 接收到这个请求，如果匹配 DispatcherServlet 在 web.xml 中配置的映射路径，Tomcat 就将请求转交给 DispatcherServlet 处理；

4. DispatcherServlet 从容器中取出所有 HandlerMapping 实例（每个实例对应一个 HandlerMapping 接口的实现类）并遍历，每个 HandlerMapping 会根据请求信息，通过自己实现类中的方式去找到处理该请求的 Handler (执行程序，如Controller中的方法)，并且将这个 Handler 与一堆 HandlerInterceptor (拦截器) 封装成一个 HandlerExecutionChain 对象，一旦有一个 HandlerMapping 可以找到 Handler 则退出循环；

5. DispatcherServlet 取出 HandlerAdapter 组件，根据已经找到的 Handler，再从所有 HandlerAdapter 中找到可以处理该 Handler 的 HandlerAdapter 对象；

6. 执行 HandlerExecutionChain 中所有拦截器的 preHandler() 方法，然后再利用 HandlerAdapter 执行 Handler ，执行完成得到 ModelAndView，再依次调用拦截器的 postHandler() 方法；

7. 利用 ViewResolver 将 ModelAndView 或是 Exception（可解析成 ModelAndView）解析成 View，然后 View 会调用 render() 方法再根据 ModelAndView 中的数据渲染出页面；

8. 最后再依次调用拦截器的 afterCompletion() 方法，这一次请求就结束了。

   

   ### 3.自动装配类型(在xml配置文件中的使用)

   | 自动装配类型 | 说明                                                         |
   | ------------ | ------------------------------------------------------------ |
   | byName       | 根据名称进行自动匹配                                         |
   | byType       | 根据类型进行自动匹配                                         |
   | constructor  | 与byType类似，只不过它是针对构造函数注入                     |
   | autodetect   | 根据Bean的自省机制决定采用byType还是constructor进行自动装配。如果Bean提供了默认的构造函数，则采用byType；否则采用constructor |

   ### 4.Bean的作用域

   | 类型          | 说明                                                         |
   | ------------- | ------------------------------------------------------------ |
   | singleton     | 在Spring IoC容器中仅存在一个Bean实例，Bean以单实例的方式存在（默认） |
   | protorype     | 每次从容器中调用Bean时，都返回一个新的实例，即每次调用getBean()时，相当于执行new 的操作 |
   | request       | 每次HTTP请求都会创建一个姓的Bean，该作用域仅适用于WebApplicationContext环境 |
   | session       | 通一个HTTP Session共享一个Bean，不同的Http Session使用不同的Bean，该作用域仅适用于WebApplicationContext环境 |
| globalSession | 同一个全局Session共享一个Bean，一般用于Portlet应用环境，该作用域仅适用于WebApplicationContext环境 |
   
   

### 5.定义bean的注解（@Component，@Repository，@Service，@Controller）

| @Component  | 被标注的类可以被spring容器识别 |
| ----------- | ------------------------------ |
| @Repository | 用于对DAO实现类进行标注        |
| @Service    | 用于对Service实现类进行标注    |
| @Controller | 用于对Controller实现类进行标注 |

6.自动装配Bean

| @Autowired          | 在变量和方法进行自动注入，@Autowired(required=false)表示即使没有匹配的Bean也不要异常抛出 |
| ------------------- | ------------------------------------------------------------ |
| @Qualifier          | 指定注入Bean的名称，容器有一个以上匹配的Bean，可以@Qualifier("userDao")指定注入Bean的名称 |
| @Lazy及@Autowired   | 会延迟到调用此属性时才会注入属性值                           |
| @Scope("protorype") | 指定Bean的作用范围,注解类                                    |

Bean的生命过程 

@POSTConstruct 在Bean被调用时触发，注解方法

@PreDestroy 在Bean被销毁时触发，注解方法

7.AOP术语

| 连接点         | aop可以切入的方法，但是不一定要切的方法 |
| -------------- | --------------------------------------- |
| 切点           | aop要切入的方法                         |
| 增强（Advice） | 织入连接点的一段代码                    |
| 目标对象       | 增强逻辑织入目标类                      |

### 8.aop的使用

如果项目采用java 5.0则使用@AspectJ，如果项目只能使用低版本的JDK,则使用<aop:aspect>;如果使用低版本的spring aop则使用<aop:advisor>复用已存在的Advice类;如果项目只能使用低版本的spring，那么就只能使用Advisor

### 9.SPRINGdao异常体系类

| CleanupFailureDataAccessException       | DAO操作成功执行，但在释放数据资源时发生异常，如关闭Connection时发生异常 |
| --------------------------------------- | ------------------------------------------------------------ |
| ConcurrencyFailureException             | 表示在进行并发数据操作时发生异常，如乐观锁无法获取，悲观锁无法获取，死锁引发的失败等 |
| DataAccessResourceFailureException      | 访问数据资源时失败，如无法获取数据连接，无法获取Hibernate的会话等 |
| DataRetrievalFailureException           | 获取数据失败，如找不到对应的主键的数据，使用了错误的列索引等 |
| DataSourceLookupFailureException        | 无法从JNDI中查找到数据源                                     |
| DataIntegrityViolationException         | 当数据操作无法了数据一致性限制时抛出的异常，如插入重复的主键，引用不存在的外键等 |
| InvalidDataAccessApiUsageException      | 不正确地调用某一持久化技术时抛出的异常，如在Spring JDBC中查询对象，在调用前必须进行编译操作，如果是忘记就会产生该异常。这种异常不是由底层数据资源产生的，而是由不正确地使用持久化技术产生的 |
| IncalidDataAccessResourceUsageException | 在访问数据源时使用了不正确地方法所抛出的异常，如SQL语句错误将抛出该异常 |
| PermissionDeniedDataAccessException     | 数据访问时由于权限不足引发的异常，如仅有只读权限的用户试图进行数据更改操作时抛出该异常 |
| UncategorizedDataAccessException        | 其他未分类的异常都归到该异常中                               |

### 10.数据库事务的特性

| 原子性 | 组成事务的多个数据库操作时一个不可分割的原子单位，只有所有操作执行成功，整个事务才提交 |
| ------ | ------------------------------------------------------------ |
| 一致性 | 事务操作成功后，数据库所处的状态和它的业务规则是一致的，即数据不会被破坏。如A和B转账，无论操作成功与否，A和B的总额是不变的 |
| 隔离性 | 在并发数据操作时，不同的事务拥有各自的数据空间，他们的操作不会对对方产生干扰。不同的隔离级别对应不同的干扰程度，隔离级别越高，数据一致性越好，但并发性越弱 |
| 持久性 | 一旦事务提交成功后，事务中所有的数据操作都必须被持久化到数据库中。即使在提交事务后，数据库马上崩溃，在数据库重启时，也必须保证能通过某种机制恢复数据 |

数据库管理系统采用重执行日志来保证原子性，一致性和持久性，采用数据库锁机制保证事务的隔离性，当多个事务试图对相同的数据进行操作时，只有持有锁的事务才能操作数据，直到前一个事务完成后，后面的事务才有机会对数据进行操作

### 11、数据库隔离级别

1.脏读（dirty read）

A事务读取了B事务尚未提交更改的数据，并在这个数据基础上进行操作。恰巧B事务回滚，那么A事务读取到的数据是不被承认的，造成了500元的丢失。

| 转账事务A               | 转账事务B                |
| ----------------------- | ------------------------ |
|                         | 开始事务                 |
| 开始事务                |                          |
|                         | 查询到账户余额为1000     |
|                         | 取出500，把余额改成500   |
| 查询到余额为500（脏读） |                          |
|                         | 撤销事务，余额恢复为1000 |
| 汇入100，把余额改为600  |                          |
| 提交事务                |                          |

2.不可重复读（unrepeatable read）

在同一个事务中读取同一个数据获得了不一样的值。

| 取款事务A                                          | 取款事务B              |
| -------------------------------------------------- | ---------------------- |
|                                                    | 开始事务               |
| 开始事务                                           |                        |
|                                                    | 查询账户余额为1000     |
| 查询账户余额为1000                                 |                        |
|                                                    | 取出100，把余额改成900 |
|                                                    | 提交事务               |
| 查询账户余额为900（和上面事务A查询到的数据不一样） |                        |

3.幻象读（phantom read ）

| 统计金额事务A                     | 统计金额事务B               |
| --------------------------------- | --------------------------- |
|                                   | 开启事务                    |
| 开启事务                          |                             |
| 统计总存款数为10000               |                             |
|                                   | 新增一个存款账户，存款为100 |
|                                   | 提交事务                    |
| 再次统计总存款数为10100（幻象读） |                             |

幻象读和不可重复读是两种概念，前者是指读到了其他已经提交事务的新增数据，而后者是指读到了已经提交事务的更改数据（更改或者删除）。为了避免这种情况，采取的对策是不同的，防止读到更改数据，只需对操作的数据添加行级锁，阻止操作中的数据发生改变；而防止读到新增数据，则往往需要添加表级锁，将整张表锁定，防止新增数据。

4.第一类丢失更新

A事务撤销时，把已经提交的B事务的更新数据掩盖了

| 取款事务A                                           | 取款事务B               |
| --------------------------------------------------- | ----------------------- |
| 开启事务                                            |                         |
|                                                     | 开启事务                |
| 查询账户余额为1000                                  |                         |
|                                                     | 查询账户余额为1000      |
|                                                     | 汇入100，把余额改为1100 |
|                                                     | 提交事务                |
| 取出100，把余额改为900                              |                         |
| 撤销事务                                            |                         |
| 余额恢复为1000（丢失更新，把事务B汇入的金额丢失了） |                         |

5.第二类丢失更新

| 转账事务A                                       | 转账事务B              |
| ----------------------------------------------- | ---------------------- |
|                                                 | 开启事务               |
| 开启事务                                        |                        |
|                                                 | 查询账户余额为1000     |
| 查询账户余额为1000                              |                        |
|                                                 | 取出100，把余额改为900 |
|                                                 | 提交事务               |
| 汇入100                                         |                        |
| 提交事务                                        |                        |
| 把余额改为1100（丢失更新，事务B的更新被掩盖了） |                        |

### 12、数据库锁机制

### 13、事务的隔离级别

尽管数据库为用户提供了锁的DML操作，但是直接使用锁管理是非常繁琐的，因此数据库为用户提供了自动锁机制。只要用户指定会话的事务隔离级别，数据库就会分析事务中的SQL语句，然后自动为事务操作的数据资源添加合适的锁。

| 隔离级别        | 脏读   | 不可重复读 | 幻象读 | 第一类丢失更新 | 第二类丢失更新 |
| --------------- | ------ | ---------- | ------ | -------------- | -------------- |
| READ UNCOMMITED | 允许   | 允许       | 允许   | 不允许         | 允许           |
| READ COMMITTED  | 不允许 | 允许       | 允许   | 不允许         | 允许           |
| REPEATABLE READ | 不允许 | 不允许     | 允许   | 不允许         | 不允许         |
| SERIALIZABLE    | 不允许 | 不允许     | 不允许 | 不允许         | 不允许         |

事务的隔离级别和数据库并发性是对立的，越高的隔离级别并发性就越低。

### 14.spring事务的高级使用

### 15.Quartz,JDKTime,Executor

### 16.配置DispatcherServlet

`

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 修改 Spring MVC 配置文件的位置和名称 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-web.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    </web-app>
```

`

### 17.配置视图解析器

`

```
<bean id="viewResolver"
class="org.springframework.web.servlet.view.InternalResourceViewResolver">
<property name="prefix" value="/WEB-INF/jsp/"/>
<property name="suffix" value=".jsp"/>
</bean>
```

`

### 18.注解驱动控制器

| @RequestMapping   | 通过前期URL进行映射,如@RequestMapping("/getInfo")                                                         @RequestMapping除了可以使用请求URL映射外，还可以使用请求方法，请求参数（报文体和URL包含的请求参数）映射请求 @RequestMapping(path="/delete",method=RequestMethod.Post,headers="content-type=text/*") |
| ----------------- | ------------------------------------------------------------ |
| @PathVariable     | 通过@PathVariable可以将URL中的占位符参数绑定到控制器处理方法的入参中 @RequestMapping("/{bookId}")                                                                                            public String showinfo(@PathVariable("bookId")String bookId){} |
| @RequestParam     |                                                              |
| @RequestBody      |                                                              |
| @CookieValue      | public ModelAndView getInfo(@CookieValue("JSESSIONID")String sessionId)将Cookie值绑定到入参中 |
| @RequestHeader    | publice ModelAndView getInfo(@RequestHeader("Accept-Language")String accpetLanguage)将报文头属性绑定到入参中 |
| @ResponseBody     |                                                              |
| @RestController   | @ResponseBody和@Controller的集合                             |
| @ModelAttribute   |                                                              |
| @ExceptionHandler |                                                              |

### 19.`<mvc:resources>和<mvc:default-servelet-handler>`