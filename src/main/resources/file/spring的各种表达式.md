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