# springlearn

1. [spring bean学习](https://www.imooc.com/video/3665)   

   1.[什么是ioc](#是控制反转)
   
2.[spring配置文件](#spring的配置)
   
3.[导入spirng的配置文件](#导入spirng的配置文件)
   
4.[bean 容器的初始化](#bean容器的初始化)
   
5.[Spring的注入](#spring的注入)
   
   6.[Bean配置项]()
   
   7.[Bean的作用域]()
   
   8.[Bean的生命周期]()
   
   **1.什么是ioc**
   
     ioc是控制反转，应用本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护。DI（依赖注入）是其一种实现方式。目的是创建对象并组装对象之间的关系
   
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
   
     ​     bean的初始化：配置init-method
   
     ```
       <bean id="number" class="com.wind.spring.bean.Person"
               init-method="init" />
       public class Person {
         public void init(){
             System.out.println("bean的初始化");
         }
     }
     ```
   
     ​	bean的销毁：配置destroy-method
   
     ```
     <bean id="person" class="com.wind.spring.bean.Person"
               destroy-method="destroy"/>
     public class Person {
         public void destroy() {
             System.out.println("bean的销毁");
         }
     }
     ```
   
     
