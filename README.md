# springlearn

1. [spring bean学习](https://www.imooc.com/video/3665)   

   - [什么是ioc](#是控制反转)
   - [spring配置文件](#spring的配置)
- [导入spirng的配置文件](#导入spirng的配置文件)
   - [bean 容器的初始化](#bean容器的初始化)
- [Spring的注入](#spring的注入)
   
1.什么是ioc
   
  ioc是控制反转，应用本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护。DI（依赖注入）是其一种实现方式。目的是创建对象并组装对象之间的关系
   
   2.spring的配置
   
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
   
   3.导入spirng的配置文件
   
   ```
   ApplicationContext ac = new
                   ClassPathXmlApplicationContext("spring/applicationContext.xml");
   UserBean userBean = (UserBean) ac.getBean("userbean");
   ```
   
   4.bean容器的初始化
   
   ​	基础包
   
   ​		org.springframework.beans和org.springframework.context,其中BeanFactory提供了配置架构和基本功能，加载并初始化bean，ApplicationContext保存了bean对象。Classpath为相对路径就是相对工程来说的位置
   
   ​	初始化
   
   ```
   FileSytemXmlApplicationContext context = new FileSystemXmlApplicationContext("F:/appcontext.xml");
   ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context.xml")
   ```
   
   5.spring的注入
   
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
   
   ​	

