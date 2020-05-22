## Spring MVC
1. <details>
   <summary>Spring MVC的jar包</summary><pre><code><span style="color:#FF7F50;">- spring-aop-3.2.2.jar                        AOP</span>
   <span style="color:#FF7F50;>- spring-aspects-3.2.2.jar                    AOP</span>
   <span style="color:#FF7F50;>- spring-beans-3.2.2.jar                      核心包</span>
   <span style="color:#FF7F50;>- spring-context-3.2.2.jar                    扩展包</span>
   <span style="color:#FF7F50;>- spring-context-support-3.2.2.jar            对扩展包支持</span>
   <span style="color:#FF7F50;>- spring-core-3.2.2.jar                       核心包</span>
   <span style="color:#FF7F50;>- spring-expression-3.2.2.jar spring          表达式</span>
   <span style="color:#FF7F50;>- spring-web-3.2.2.jar                        web b/s</span>
   <span style="color:#FF7F50;>- spring-webmvc-3.2.2.jar                     springmvc</span>
   <span style="color:#FF7F50;>- com.springsource.org.aopalliance-1.0.0.jar                  AOP</span>
   <span style="color:#FF7F50;>- com.springsource.org.apache.commons.logging-1.1.1.jar       通用日志</span>
   </code></pre>
   </details>
   
2. <details>
   <summary> 配置 web.xml 文件，主要是配置 DispatcherServlet，即核心分发器</summary>
   <pre><code>< ?xml version="1.0" encoding="UTF-8"?>
   < web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
         <!-- 配置 DispatcherServlet，对所有后缀为action的url进行过滤 -->
         < servlet>
         < servlet-name>dispatcher< /servlet-name>
         < servlet-class>org.springframework.web.servlet.DispatcherServlet< / servlet-class>
         <!-- 修改 Spring MVC 配置文件的位置和名称 -->
         < init-param>
            < param-name>contextConfigLocation< / param-name>
            < param-value>classpath:spring/spring-servlet.xml< /  param-value>
         < / init-param>
    < / servlet>
    < servlet-mapping>
        < servlet-name>dispatcher< / servlet-name>
        < url-pattern>/< / url-pattern>
    < / servlet-mapping>
    < welcome-file-list>
        < welcome-file>index.jsp< / welcome-file>
    < / welcome-file-list>
    <!-- 中文过滤器 -->
    < filter>
        < filter-name>CharacterEncodingFilter< / filter-name>
        < filter-class>org.springframework.web.filter.CharacterEncodingFilter< / filter-class>
        < init-param>
            < param-name>encoding< / param-name>
            <!-- 设置编码格式 -->
            < param-value>utf-8< / param-value>
        < / init-param>
    < / filter>
    < filter-mapping>
        < filter-name>CharacterEncodingFilter< / filter-name>
        < url-pattern>/*< / url-pattern>
    < / filter-mapping>
    < / web-app> 
    </code></pre>
   </details>
   
3. <details>
   <summary>建立 action-servlet.xml 文件，主要是声明 Controller 和配置 ViewResolver，即控制器和视图解析器</summary>
   <pre><code>< ?xml version="1.0" encoding="UTF-8"?>
 < beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans-  3.2.xsd" >
       <!-- 声明 Controller -->
   < context:component-scan base-package="com.wind.spring.springmvc"/> <!-- 内部资源视图解析器，前缀 + 逻辑名 + 后缀 -->
      < bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        < property name="prefix" value="/WEB-INF/pages/"/>
     < property name="suffix" value=".jsp"/>
    < /bean>
   < /beans>
   </code></pre>
   </details>
   


4. 常用注解

   - **@RequestMapping** 用来处理请求地址映射的注解

     - value 值

     - method 请求的方法

     - headers 根据设置的请求头来缩小请求映射的范围，只接受"content-type=text/plain"的内容

       ```
        @RequestMapping(value = "/head", headers = {  
               "content-type=text/plain",  
               "content-type=text/html"  
           })
       ```

     - params 可以通过指定params元素来进一步缩小请求映射的范围

       ```
         @RequestMapping(value = "/fetch", params = {  
               "personId=10"  
           })  
       ```

     - consumes 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;

     - produces:  指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；

   - **@ResponseBody** 将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。

     - required 是否需要正文内容

   - **@Controller** 指示Spring类的实例是一个控制器

     - value 值

   - **@RequestParam** 将请求参数绑定到你控制器的方法参数上

     ```
     public String getParam3(@RequestParam("user") String username,
                                 @RequestParam("password") String pwd) {}
     ```

     - value 参数名
     - required 是否要包含该参数
     - defaultValue 设置参数默认值

   - **@PathVariable** 将 URL 中占位符参数绑定到控制器处理方法的入参中

     ```
      @RequestMapping(path = "get5/{userName}/info", method = RequestMethod.GET)
         public String req6(@PathVariable(name = "userName") String userName) {}
     ```

     需要注意的是url上面的参数是可以映射到bean的，在form表单中的参数也可以映射到bean

5. 拦截器(Interceptor)

   用于在某个方法被访问之前进行拦截，然后在方法执行之前或之后加入某些操作，其实就是AOP的一种实现策略。它通过动态拦截Action调用的对象，允许开发者定义在一个action执行的前后执行的代码，也可以在一个action执行前阻止其执行。

   <details>
   <summary>拦截器接口HandlerInterceptor的使用</summary><pre><code>
   /**
    * springmvc拦截器的使用
    *
    * @需要在spring的xml配置文件中写在<mvc:interceptors>，
    */
   public class LoginInterceptor implements HandlerInterceptor {
       /**
        * 最先进入
        * 拦截器拦截的请求首先进入这个方法，处理完成时候会处理请求接口内部的方法*/
       @Override
       public boolean preHandle(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o)
               throws Exception {
           User user = (User) httpServletRequest.getSession().getAttribute("user_session");
           System.out.println("1:interceptor==>preHandle");
           if (user == null) {
               httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/api/interceptor/tologin");
               return true;
           }
           return true;
       }
       /*** 在请求的接口方法完成之后会执行，即2:interceptor===>search*/
       @Override
       public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                              ModelAndView modelAndView) throws Exception {
           System.out.println("3:interceptor===>postHandle");
       }
       /*** 拦截器最后执行*/
       @Override
       public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                   Object o, Exception e) throws Exception {
           System.out.println("4:interceptor===>afterCompletion");
       }
   }
   </code></pre>
   </details>

   <details>
   <summary>拦截器接口WebRequestInterceptor的使用</summary><pre><code>
   //与HandlerInterceptor的区别在于无法终止访问请求
   public interface WebRequestInterceptor {
       //返回类行为void，与HandlerInterceptor区别就体现在这里
       void preHandle(WebRequest request) throws Exception;
       void postHandle(WebRequest request, ModelMap model) throws Exception;
       void afterCompletion(WebRequest request, Exception ex) throws Exception;
   }
   </code></pre>
   </details>

6. 过滤器(Filter)

