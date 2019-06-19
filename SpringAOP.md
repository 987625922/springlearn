### Spring AOP

1.Spring AOP简介

2.配置切面aspect

**1.Spring AOP简介**

AOP名为面向切面的编程，通过预编译方式和运行期动态代理实现程序功能的一种技术，主要功能是日志记录，性能统计，安全控制，事务处理，异常处理等等

- 预编译 Aspectj
- 运行期动态代理（JDK动态代理，CGLib动态代理）SpringAOP,JbosAOP

 spring的AOP目前只支持方法执行连接点（通知Spring Bean的方法执行）

**2.配置切面aspect**

Spring所有的切面和通知器都必须放在一个\<aop:config\>内（可以配置包含多个\<aop:config\>元素），每一个\<aop:config\>可以包含pointcut，advisor和aspect元素**（他们必须按照这个顺序进行声明）**

```
 <aop:config>
        //把aspect这个bean作为一个切面来声明
        <aop:aspect id="aspectAOP" ref="aspect">
        </aop:aspect>
    </aop:config>
```

