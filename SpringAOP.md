### Spring AOP

1.Spring AOP简介

2.配置切面aspect

3.advice

4.Introductions(简介)

5.advisors

**1.Spring AOP简介**

AOP名为面向切面的编程，通过预编译方式和运行期动态代理实现程序功能的一种技术，主要功能是日志记录，性能统计，安全控制，事务处理，异常处理等等

- 预编译 Aspectj
- 运行期动态代理（JDK动态代理，CGLib动态代理）SpringAOP,JbosAOP

 spring的AOP目前只支持方法执行连接点（通知Spring Bean的方法执行）

**2.配置切面aspect**

Spring所有的切面和通知器都必须放在一个\<aop:config\>内（可以配置包含多个\<aop:config\>元素），每一个\<aop:config\>可以包含pointcut，advisor和aspect元素**（他们必须按照这个顺序进行声明）**

```
 <aop:config>
        把aspect这个bean作为一个切面来声明
        <aop:aspect id="aspectAOP" ref="aspect">
        </aop:aspect>
    </aop:config>
```

aop:pointcut（切点）

横切面要切入的方法

```
<aop:config>
        <!--把aspect这个bean作为一个切面来声明-->
        <aop:aspect id="aspectAOP" ref="myaspect">
            <!--配置指定包下以Biz结尾的bean的所有方法为切点-->
            <aop:pointcut id="aspectBean" expression="execution(* com.wind.spring.springaop.*Biz.*(..))"/>
        </aop:aspect>
    </aop:config>
```

**3.advice**

aop:before 

```
<aop:config>
        <!--把aspect这个bean作为一个切面来声明-->
        <aop:aspect id="aspectAOP" ref="myaspect">
            <!--配置指定包下以Biz结尾的bean的所有方法为切点-->
            <aop:pointcut id="aspectBean" expression="execution(* com.wind.spring.springaop.*Biz.*(..))"/>
            <!-- before为在切点之前执行的方法,执行方法的切点的id为pointcut-ref,方法的名称为横切面类中的before -->
            <aop:before method="before" pointcut-ref="aspectBean"/>
        </aop:aspect>
    </aop:config>
```

aop:after-returning

after-returning为在切点方法执行完成之后调用

```
<aop:config>
        <!--把aspect这个bean作为一个切面来声明-->
        <aop:aspect id="aspectAOP" ref="myaspect">
            <!--配置指定包下以Biz结尾的bean的所有方法为切点-->
            <aop:pointcut id="aspectBean" expression="execution(* com.wind.spring.springaop.*Biz.*(..))"/>
            <!-- before为在切点之前执行的方法,执行方法的切点的id为pointcut-ref,方法的名称为横切面类中的before -->
            <aop:before method="before" pointcut-ref="aspectBean"/>
            <!-- after-returning为在切点方法执行完成之后调用 -->
            <aop:after-returning method="afterReturning" pointcut-ref="aspectBean" />
        </aop:aspect>
    </aop:config>
```

afterThrowing

在方法抛出异常后执行

```
<!-- 方法抛出异常之后执行 -->
            <aop:after-throwing method="afterThrowing" pointcut-ref="aspectBean"/>
```

aop:after

在afterReturning之后执行，也被称为final的advice，就算抛出异常也执行

```
<!--在afterReturning之后执行，也被称为final的advice，就算抛出异常也执行-->
<aop:after method="after" pointcut-ref="aspectBean"/>
```

around advice（环绕通知）

通知方法的第一个参数必须是ProceedingJoinPoint类型

```
<!-- 环绕方法的通知 -->
<aop:around method="around" pointcut-ref="aspectBean"/>
```

```
<!-- 方法带参数的环绕通知 -->
<aop:around method="aroundInit" pointcut="execution(* com.wind.spring.springaop.AspectBiz.init(String,int)) and args(bizName,times)"/>
```

4.Introductions（简介）

简介允许一个切面声明一个实现指定接口的通知对象，并且提供了一个接口实现类来代表这些对象

由\<aop:aspect\>中的\<aop:declare-parents\>元素声明该元素用于声明所匹配的类型拥有一个新的parent（因此得名），就是说通过AOP的方式给指定的类指定一个新的父类

achema-defined aspects 只支持singleton model

例子如下：（和项目中的springaop包下的Fit和FitImpl）

```
<!-- introductions -->
            <!-- implement-interface为指定接口 default-impl为接口实现类 types-matching为匹配的对象-->
            <!-- type-matching匹配到后会为匹配到的类指定一个新的父类 -->
            <aop:declare-parents
                    types-matching="com.wind.spring.springaop.*(+)"
                    implement-interface="com.wind.spring.springaop.introductions.Fit"
                    default-impl="com.wind.spring.springaop.introductions.FitImpl"
            />
```

**5.advisors**

advisor是一个小的自包含的方面，只有一个advice

切面自身通过一个bean表示，并且必须实现某个advice接口，同时，advisor也可以很好的利用AspectJ的切入点表达式

Spring通过配置文件中\<aop:advisor\>元素支持advisor实际使用中，大多数情况下它会和transactional（事务） advice配合使用