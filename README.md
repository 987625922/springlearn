# springlearn

1. [spring bean学习](https://www.imooc.com/video/3665)   

   - [什么是ioc](#是控制反转)
   - [spring配置文件](#spring的配置)

   1.什么是ioc

   ​	ioc是控制反转，应用本身不负责依赖对象的创建和维护，而是由外部容器负责创建和维护。DI（依赖注入）是其一种实现方式。目的是创建对象并组装对象之间的关系

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

   

