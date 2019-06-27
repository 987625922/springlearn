## Spring MVC
##### **1.Spring MVC的jar包**
##### **2.Spring MVC配置**
##### 在Spring MVC框架中，从“Request（请求）”开始，依次进入“DispatcherServlet（核心分发器）” —> “HandlerMapping（处理器映射）” —> “Controller（控制器）” —> “ModelAndView（模型和视图）” —> “ViewResolver（视图解析器）” —> “View（视图）” —> “Response（响应）”结束，其中DispatcherServlet、HandlerMapping和ViewResolver 只需要在XML文件中配置即可，从而大大提高了开发的效率，特别是对于 HandlerMapping 框架为其提供了默认的配置。

##### **1.Spring MVC的jar包**
```
 spring-aop-3.2.2.jar                        AOP
      spring-aspects-3.2.2.jar                    AOP
      spring-beans-3.2.2.jar                      核心包
      spring-context-3.2.2.jar                    扩展包
      spring-context-support-3.2.2.jar            对扩展包支持
      spring-core-3.2.2.jar                       核心包
      spring-expression-3.2.2.jar spring          表达式
      spring-web-3.2.2.jar                        web b/s
      spring-webmvc-3.2.2.jar                     springmvc
      
      com.springsource.org.aopalliance-1.0.0.jar                  AOP
      com.springsource.org.apache.commons.logging-1.1.1.jar       通用日志
```
##### **2.Spring MVC**  
#####配置 web.xml 文件，主要是配置 DispatcherServlet，即核心分发器
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!--  spring应用上下文，理解层次化的applicationcontext  -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <!--到类目录下寻找我们的配置文件-->
        <param-value>WEB-INF/dispatcher-servlet.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!-- 注册springmvc框架核心控制器 -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <!-- 映射路径为全部请求 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 中文过滤器 -->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <!-- 设置编码格式 -->
            <param-value>utf-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```   
##### 建立 action-servlet.xml 文件，主要是声明 Controller 和配置 ViewResolver，即控制器和视图解析器：
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

    <!-- 声明 Controller -->
    <context:component-scan base-package="com.wind.spring.web"/>

    <!-- 内部资源视图解析器，前缀 + 逻辑名 + 后缀 -->
    <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/pages/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```