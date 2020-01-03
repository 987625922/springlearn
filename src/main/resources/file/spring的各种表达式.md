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