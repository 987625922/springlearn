## 一.Bean装配的基础

1.[什么是ioc](#是控制反转)

2.[spring配置文件](#spring的配置)

3.[导入spirng的配置文件](#导入spirng的配置文件)

4.[bean 容器的初始化](#bean容器的初始化)

5.[Spring的注入](#spring的注入)

6.[Bean配置项]()

7.[Bean的作用域]()

8.[Bean的生命周期]()

9.[Aware]()

10.[bean的自动装配]()

11.Resources

## 二.Bean管理的注解实现

1.Classpath扫描与组件管理

2.类的自动检测及Bean的注册

3.\<context:annotation-config/\>

4.使用过滤器进行自定义扫描

5.定义Bean

6.作用域(Scope)

7.@Required(注解bean的setter方法)

8.@Autowired

9.@Qualifier

10.@Bean

11.@ImportResource和@Value注解

12.@Bean and @Scope

13.基于泛型的自动装配

14.CustomAutowireConfigurer

15.Spring对JSR支持的说明

**1.什么是ioc**

```
 ioc是控制反转，应用本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护。DI（依赖注入）是其一种实现方式。目的是创建对象并组装对象之间的关系
```

   



**2.spring的配置**

```
<!--xml 版本的说明-->
   <?xml version="1.0" encoding="UTF-8"?>
   <!-- spring 的命名空间 -->
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
   	http://www.springframework.org/schema/beans/spring-beans.xsd
   	http://www.springframework.org/schema/context
   	http://www.springframework.org/schema/context/spring-context.xsd"
   >
   <!-- id 为bean的唯一标识，class为bean对应的类 -->   
   <bean id="oninterface" class="com.test.ioc.OneInterface"></bean>
   </beans>
```

   

**3.导入spirng的配置文件**

```
   ApplicationContext ac = new
                   ClassPathXmlApplicationContext("spring/applicationContext.xml");
   UserBean userBean = (UserBean) ac.getBean("userbean");
```

   

**4.bean容器的初始化**

   ​	基础包

   ​		org.springframework.beans和org.springframework.context,其中BeanFactory提供了配置架构和基本功能，加载并初始化bean，ApplicationContext保存了bean对象。Classpath为相对路径就是相对工程来说的位置

   ​	初始化

```
   FileSytemXmlApplicationContext context = new FileSystemXmlApplicationContext("F:/appcontext.xml");
   ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml")
```

   

**5.spring的注入**

   ​	spring的注入指的是在启动spring容器加载bean配置的时候，完成对变量的赋值行为（常见的注入有2种，设值注入和构造注入）

   ​	设值注入(就是通过set的方法)和构造器注入（就是通过构造器注入）

```
   <bean id="userbean" class="com.wind.spring.bean.UserBean">
           <!-- 设值注入 -->
           <property name="book" ref="book"/>
           <!-- 构造器注入-->
           <constructor-arg name="beanName" value="这个是bean的名称" />
       </bean>
```

   

**6.Bean的配置项**

   ​	常用的如下：Id（整个ioc容器中bean的唯一标识）,Class（具体要实例化的类）,Scope（范围，作用域）,Constructor arguments（构造器的参数，构造器注入中说到）,Properties（类的属性，设值注入中用到）,Autowiring mode（自动装配模式）,lazy-initialization mode（懒加载模式）,Initialization/destruction method（初始化和销毁的方法）

   

**7.Bean的作用域**

- singleton：(默认)单例模式，一个Bean容器中只存在一份

- prototype：原型模式，每次通过容器的getBean方法获取prototype定义的Bean时，都将产生一个新的Bean实例

- request：对于每次HTTP请求，使用request定义的Bean都将产生一个新实例，即每次HTTP请求将会产生不同的Bean实例。只有在Web应用中使用Spring时，该作用域才有效

- session：对于每次HTTP Session，使用session定义的Bean豆浆产生一个新实例。同样只有在Web应用中使用Spring时，该作用域才有效

- globalsession：每个全局的HTTP Session，使用session定义的Bean都将产生一个新实例。典型情况下，仅在使用portlet context的时候有效。同样只有在Web应用中使用Spring时，该作用域才有效

  ```
  <bean id="number" class="com.wind.spring.bean.Person"
            scope="singleton" />
  ```

  

   **8.Bean的生命周期**

   生命周期（定义（在xml文件中写上bean的class和id），初始化（导入applicationContext文件，并生成bean的实例），使用（调用bean容器的方法），销毁（bean容器停止的时候去销毁由当前这个bean容器创建的实例））

   bean的初始化：配置init-method
     
    

```
   <bean id="number" class="com.wind.spring.bean.Person"
           init-method="init" />
   public class Person {
     public void init(){
         System.out.println("bean的初始化");
     }

  }
}
```

   

  bean的销毁：配置destroy-method
     
     

```
 <bean id="person" class="com.wind.spring.bean.Person"
           destroy-method="destroy"/>
 public class Person {
     public void destroy() {
         System.out.println("bean的销毁");
     }

  }
}
```

   **9.Aware**

   spring中提供了一些以Aware结尾的接口，实现了Aware接口的bean在初始化之后，可以获取相应资源，通过Aware接口，可以对Spring相应资源进行操作（一定要慎重 ），为对Spring进行简单的扩展提供了方便的入口 
     
   ApplicationContexteAware
     
     

```
 <bean id="moocApplicationContext" class="com.wind.spring.aware.MoocApplicationContext"/>
 /*
  * 得到ioc容器的applicationcontext，在容器初始化时可对applicationcontext进行操作
  * */
 public class MoocApplicationContext implements ApplicationContextAware {
     @Override
     public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       System.out.println(applicationContext.getBean("moocApplicationContext"));
     }

  }
}
```

   BeanNameAware

```
 <bean id="moocBeanName" class="com.wind.spring.aware.MoocBeanName"/>
 public class MoocBeanName implements BeanNameAware {
     @Override
     public void setBeanName(String name) {
         System.out.println();
     }
 }
```

​     
​     
​     
   **10.bean的自动装配**
​     

- NO：默认，不做任何操作

- byname：根据属性名来自动装配。此选项将检查容器并根据名字查找与属性完全一致的bean，并将其与属性自动装配

- byType：如果容器中存在一个与指定属性类型相同的bean，那么将该属性自动装配；如果存在多个该类型bean，那么抛出异常，并指出不能使用byType方式进行自动装配；如果没有找到相匹配的bean，则什么事都不发生

- Constructor：与byType方式类似，不同之处在于它应用于构造器测试。如果容器中没有找到与构造器类型一致的bean，那么抛出异常

  ```
  <!-- 在xml头添加default-autowire="byName" 或者在bean中配置autowire="constructor" -->
      <bean id="autobean" class="com.wind.spring.bean.AutoWiringBean" autowire="constructor"/>
  ```

​     
   **11.Resources**
​     
   针对资源文件的统一入口
​     

- UrlResource：URL对应的资源，根据一个URL地址即可构建

- ClassPathResource：获取类路径下的资源文件

- FileSystemResource：获取文件系统里面的资源

- ServletContextResource：ServletContext封装的资源，用于访问ServletContext环境下的资源

- InputStreamResource：针对于输入流封装的资源

- ByteArray：针对于字节数组封装的资源

    

## Bean管理的注解实现

- Classpath扫描与组件管理

- 类的自动检测与注册Bean

- \<context:annotation-config/\>

- @Component,@Repository,@Service,@Controller

- @Required

- @Autowired

- @Qualifier

- @Resource

    

   **1.Classpath扫描与组件管理**
     
   spring3.0开始，spring提供了通过java注解的方式定义bean，大大简化了xml文件，例如：在java的类名头定义@Component,@Bean,@Import,@DependsOn
     
   @Component是一个通用注解，可用于任何bean
     
   @Repository,@Service,@Controller是更有针对性的注解
     

- @Repository通常用于注解DAO类，即持久层

- @Service通常用于注解service类，即服务层

- @Controller通常用于Controller类，即控制层（MVC）

  ​    

   **2.类的自动检测及Bean的注册**
     
   Spring可以自动检测类并注册Bean到ApplicationContext中,不需要在配置文件中写bean，直接在代码中getBean（"simpleMovieLister"）就可以获取到SimpleMovieLister的实例
     
   为了能够检测到这些类并注册相应的Bean(注解了@Component等等的类)，需要在xml文件中导入<context:component-scan base-package="com.wind.spring"/>，\<context:component-scan/\>包含\<context:annotation-config/\>,通常在使用前者后，不用再使用后者，AutowiredAnnotationBeanPostProcessor和CommonAnnotationBeanPostProcessor也会被包含进来
    

```
   <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
     	http://www.springframework.org/schema/beans/spring-beans.xsd
     	http://www.springframework.org/schema/context
     	http://www.springframework.org/schema/context/spring-context.xsd"
     >
       <!-- base-package为扫描这个包下的所有类 -->
         <context:component-scan base-package="com.wind.spring"/>
         <beans/>
     
public interface MovieFinder {
     }
     
     @Repository
     public class JpaMovieFinder implements MovieFinder {
     }
     
     @Service
     public class SimpleMovieLister {
         private MovieFinder movieFinder;
         @Autowired
         public SimpleMovieLister(MovieFinder movieFinder) {
             this.movieFinder = movieFinder;
         }
     }
```

   **3.\<context:annotation-config/\>**

   通过在基于XML的配置如下标签（注意包含上下文命名空间）
     
         

```
     <?xml version="1.0" encoding="UTF-8"?>
     <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns:context="http://www.springframework.org/schema/context"
            xsi:schemaLocation="http://www.springframework.org/schema/beans
     	http://www.springframework.org/schema/beans/spring-beans.xsd
     	http://www.springframework.org/schema/context
     	http://www.springframework.org/schema/context/spring-context.xsd"
     >
         
         <context:annotation-config/>
     </beans>
```

​     
​         
​     
   **4.使用过滤器进行自定义扫描**
​     
   类被自动发现并注册bean的条件是：使用@Component，@Repository，@Service，@Controller注解或者使用@Component的自定义注解
​     
   可以通过过滤器修改上面的行为，如：下面的xml配置忽略所用的@Repository注解并用"Stub"代替
​     
   还可使用use-default-filters=“false”禁用自动发现与注册
​    
​         

```
     <beans>
             <context:component-scan base-package="com.wind.spring.spring.springfilter">
                 <context:include-filter type="regex"
                                         expression=".*Stub.*Repository"/>
                 <context:exclude-filter type="annotation"
                                         expression="org.springframework.stereotype.Repository"/>
             </context:component-scan>
         </beans>
```

​     
​         
​     
   **5.定义Bean**
​     
​         

```
     //自定义命名，指定类在装载到Bean容器中的id
     @Repository("mymoviefinderimpl")
     public class MovieFinderImpl implements MovieFinder{
     }
```

​     
​         
​     
   **6.作用域(Scope)**
​     
   通常情况下自动查找的Spring组件，其scope是singleton，Spring2.5提供了一个标识scope的注解@Scope
​     
​         

```
     @Scope("prototype")
     @Repository("moviefinderimpl")
     public class MovieFinderImpl implements MovieFinder{
     }
```

​     
​         
​     
   **7.@Required**
​     
   @Required用于注解bean属性的setter方法
​     
   这个注解表示，受影响的bean属性必须在配置时被填充，通过在bean定义或通过自动装配一个明确的属性值
​     
​          

```
     @Component
     public class RequiredMovieLister {
         private MovieFinder movieFinder;
         @Required
         public void setMovieFinder(MovieFinder movieFinder) {
             this.movieFinder = movieFinder;
         }
     }
```

​     
​         
​     
   **8.@Autowired**
​     

- 首先要知道另一个东西，default-autowire，它是在xml文件中进行配置的，可以设置为byName、byType、constructor和autodetect；比如byName，不用显式的在bean中写出依赖的对象，它会自动的匹配其它bean中id名与本bean的set**相同的，并自动装载。

- @Autowired是用在JavaBean中的注解，通过byType形式，用来给指定的字段或方法注入所需的外部资源，可注解在成员变量，构造参数，和set方法上。

- 两者的功能是一样的，就是能**减少或者消除属性或构造器参数的设置**，只是配置地方不一样而已。

- 默认情况下，如果因找不到合适的bean将会导致autowiring失败抛出异常，可以通过@Autowired(required = false)避免

   public class SimpleMovieLister {
       private MovieFinder movieFinder;
       @Autowired
       public SimpleMovieLister(MovieFinder movieFinder) {
           this.movieFinder = movieFinder;
       }
   }

- 可以使用@Autowired注解那些众所周知的解析依赖性接口，比如：BeanFactory,ApplicationContext,Environment,ResourceLoader,ApplicationEventPublisher,MessageSource

- 可以通过添加注解给需要该类型的数组的字段或方法，以提供ApplicationContext中所有特定类型的bean

- 可以用于装配key为String的Map

- 如果希望数据有序，可以让bean实现org.springframework.core.Ordered接口或使用@Order注解

- @Autowired是由spring BeanPostProcessor处理的，所以不能在自己的BeanPostProcessor或BeanFactoryPostProcessor类型应用这些注解，这些类型必须通过XML合作Spring的@Bean注解加载

   **9.@Qualifier**
     

- 按类型自动装配可能有多个bean实例的情况，可以使用spring的@Qualifier注解缩小范围（或指定唯一），也可以用于指定单独的构造器参数或方法参数

- 可用于注解集合类型变量

   @Component
   public class RequiredMovieLister {

  ```
   private MovieFinder movieFinder;
   @Autowired
   @Qualifier("jpaMovieFinder")
   public void setMovieFinder(MovieFinder movieFinder) {
       this.movieFinder = movieFinder;
   }}
  ```

  ​     

   **10.@Bean**
         
   @Bean标识一个用于配置和初始化一个由SpringIoC容器管理的新对象方法，类似于XML配置文件的<bean/>
         

  ```
  @Configuration
      public class AppConfig {
          /*
           *  @Bean就相当于如下 ,@Bean可以去指定bean的name,还可以通过initMethod和destroyMethod注册生命周期
           *  <bean id="myBook" class="com.wind.spring.bean.Book"/>
           * */
          @Bean(name = "myBook",initMethod = "init",destroyMethod = "cleanup")
          public Book myBook() {
              return new Book();
          }
      }
  ```

**11.@ImportResource和@Value注解**

使用xml的方式配置资源文件

```
<!-- jdbc配置开始  -->
    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!-- 配置c3p0数据库连接池 -->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <bean/>
```

使用类的方式加载

```
@Configuration //声明为配置文件
@ImportResource("classpath:jdbc.properties.xml") //导入资源文件
public class AppDataSourceConfig {

    @Value("${jdbc.driver}") 
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean //声明bean对象到xml
    public DataSource dataSource() {
        return new DriverManagerDataSource(jdbcUrl, user, password);
    }
}
```

**12.@Bean and @Scope**

默认@Bean是单例的，可以使用@Scope来改变bean的作用域

```
@Configuration
public class BeanAndScope {
    @Bean
    @Scope(value = "prototype")
    public Book scopeBook(){
        return new Book();
    }
}
```

**13.基于泛型的自动装配**

```
@Configuration
public class BeanG {

    @Bean(name = "storeTest")
    public Store storeTest(){
        System.out.println("s1："+s1.getClass().getName());
        System.out.println("s2："+s2.getClass().getName());
        return new StringStore();
    }

    @Autowired //基于泛型的自动装配
    private Store<String> s1;
    @Autowired //基于泛型的自动装配
    private Store<Integer> s2;

    @Bean
    public StringStore stringStore() {
        return new StringStore();
    }

    @Bean
    public IntegerStore integerStore() {
        return new IntegerStore();
    }

    @Component
    class IntegerStore implements Store<Integer> {

    }

    @Component
    class StringStore implements Store<String> {

    }

    interface Store<T> {

    }
}
```

**15.Spring对JSR支持的说明**

@Resource（和@Autowired效果一样）

Spring还支持使用JSR-250@Resource注解的变量或setter方法，这是一种在java ee 5和6中通用没事，Spring管理的对象也支持这种模式

@Resource有一个name属性，并且默认Spring解释该值作为被注入bean的名称

如果没有显示地指定@Resource的name，默认的名称是从属性名或者settter方法得出

注解提供的名字被解析为一个bean的名称，这是由ApplicationContext中的CommonAnnotationBeanPostProcessor发现并处理的

```
@Component
public class ResourceUse {
    public MovieFinder movieFinder;
    @Resource(name = "jpaMovieFinder")
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}
```

@PostConstruct and @PreDestroy(使用不多)

在spring2.5中引入支持初始化回调和销毁回调，前提是CommonAnnotationBeanPostProcessor是Spring的ApplicationContext中注册的

```
@Component
public class ResourceUse {
    public MovieFinder movieFinder;
    @Resource(name = "jpaMovieFinder")
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
    @PostConstruct
    public void populateMovieCache(){
        System.out.println("ResourceUse的bean初始化");
    }

    @PreDestroy
    public void cleaMoviceCache(){
        System.out.println("ResourceUse的bean被销毁");
    }
}  
```

使用JSR330标准注解

使用JSR330需要依赖javax.inject包

```
<dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>
```

@Inject等效于@Autowired,可以使用于类，属性，方法，构造器

如果想使用特定名称进行依赖注入，使用@Named，@Named与@Component等效

```
@Component
public class InjectUse {
    public MovieFinder movieFinder;
    //等效于@Autowired
    @Inject//如果想使用特定名称进行依赖注入，使用@Named，@Named与@Component等效
    public void setMovieFinder(@Named("jpaMovieFinder")MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
}

```

