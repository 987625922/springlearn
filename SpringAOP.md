## Spring AOP

**一.Spring AOP的使用**

1.Spring AOP简介

2.配置切面aspect

3.advice

4.Introductions(简介)

5.advisors

6.cglib

7.API

8.XML实现AOP

**二.Spring AOP API**

1.Pointcut

2.Before advice

3.ProxyFactoryBean

**三.AspectJ**

1.AspectJ介绍

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

为了定义一个advisor的优先级以便让advice可以有序，可以使用order属性来定义advisor的顺序

```
<!-- advisors -->
    <bean id="concurrentOperationExecutor" class="com.wind.spring.springaop.advisors.ConcurrentOperationExecutor">
        <property name="maxRetries" value="3"/>
        <property name="order" value="100"/>
    </bean>
    <aop:config>
        <aop:aspect id="concurrentOperationRetry" ref="concurrentOperationExecutor">
            <aop:pointcut id="idempotentOperation"
                          expression="execution(* com.wind.spring.springaop.advisors.*Service.*(..))"/>
            <aop:around method="doConcurrentOperation" pointcut-ref="idempotentOperation"/>
        </aop:aspect>
    </aop:config>
    <!-- advisors end -->
```
6.cglib
- 需要引入cglib – jar文件， 但是spring的核心包中已经包括了cglib功能，所以直接引入spring-core的jar。
- 引入功能包后，就可以在内存中动态构建子类
- 代理的类不能为final，否则报错【在内存中构建子类来做扩展，当然不能为final，有final就不能继承了】
- 目标对象的方法如果为final/static, 那么就不会被拦截，即不会执行目标对象额外的业务方法。
- 使用cglib就是为了弥补动态代理的不足【动态代理的目标对象一定要实现接口】
```
实例代码在com.wind.spring.aop.cglib的包中
```

7.API
- @Aspect							指定一个类为切面类

- @Pointcut("execution( cn.itcast.e_aop_anno..(..))")  指定切入点表达式*


- @Before("pointCut_()")				前置通知: 目标方法之前执行


- @After("pointCut_()")				后置通知：目标方法之后执行（始终执行）


- @AfterReturning("pointCut_()")		    返回后通知： 执行方法结束前执行(异常不执行)


- @AfterThrowing("pointCut_()")			异常通知:  出现异常时候执行


- @Around("pointCut_()")				环绕通知： 环绕目标方法执行

8.XML实现AOP
不需要在类中使用任何注解
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!--对象实例-->
    <bean id="userDao" class="aa.UserDao"/>
    <bean id="orderDao" class="aa.OrderDao"/>

    <!--切面类-->
    <bean id="aop" class="aa.AOP"/>

    <!--AOP配置-->
    <aop:config >

        <!--定义切入表达式，拦截哪些方法-->
        <aop:pointcut id="pointCut" expression="execution(* aa.*.*(..))"/>

        <!--指定切面类是哪个-->
        <aop:aspect ref="aop">
            <!--指定来拦截的时候执行切面类的哪些方法-->
            <aop:before method="begin" pointcut-ref="pointCut"/>
            <aop:after method="close" pointcut-ref="pointCut"/>
        </aop:aspect>
    </aop:config>
</beans>
```

**二.Spring AOP API**

**1.Pointcut**

实现之一：NameMatchMethodPointcut，根据方法名字进行匹配

成员变量：mappedNames，匹配的方法名集合

**2.Before advice**

一个简单的通知类型

只有在进入方法之前被调用，不需要MethodInvocation对象

前置通知可以在连接点执行之前插入自定义行为，但不能改变返回值

**3.ProxyFactoryBean**

创建Spring AOP代理的基本方法是使用org.springframework.aop.framework.ProxyFactoryBean,这可以完全控制切入点和通知（advice）以及他们的顺序 

```
<bean id="beforeAdvice" class="com.wind.spring.springaop.api.BeforeAdvice"/>
    <bean id="afterReturningAdvice" class="com.wind.spring.springaop.api.AfterReturningAdvice"/>
    <bean id="methodInterceptor" class="com.wind.spring.springaop.api.MethodInterceptor"/>
    <bean id="throwsAdvice" class="com.wind.spring.springaop.api.ThrowsAdavice"/>
    <bean id="bizLogicImplTarget" class="com.wind.spring.springaop.api.BizLogicImpl" ></bean>

    <bean id="pointcutBean" class="org.springframework.aop.support.NameMatchMethodPointcut">
        <property name="mappedNames">
            <list>
                <value>sa*</value>
            </list>
        </property>
    </bean>

    <bean id="defaultAdvisor" class="org.springframework.aop.support.DefaultPointcutAdvisor">
        <property name="advice" ref="beforeAdvice"/>
        <property name="pointcut" ref="pointcutBean"/>
    </bean>

    <bean id="bizLogicImpl" class="org.springframework.aop.framework.ProxyFactoryBean">
        <property name="target">
            <ref bean="bizLogicImplTarget"/>
        </property>
        <property name="interceptorNames">
            <list>
                <value>defaultAdvisor</value>
                <value>afterReturningAdvice</value>
                <value>methodInterceptor</value>
                <value>throwsAdvice</value>
            </list>
        </property>
    </bean>
```

**三.AspectJ**

1.AspectJ介绍

对@AspectJ的支持可以使用XML或Java风格的配置

```
<!-- AspectJ -->
    <aop:aspectj-autoproxy/>
```

@AspectJ切面使用@Aspect注解配置，拥有@Aspect的任何bean将被Spring自动识别并应用

用@Aspect注解的类可以有方法和字段，他们可能包括切入点（pointcut），通知（Advice）和引入（introduction）声明

@Aspect注解是不能够通过类路径自动检测发现的，所以需要配合使用@Component注释或者在xml配置bean

```
<bean id="myAspect" class="com.wind.sp"/>
@Aspect
public class NotVeryUsefulAspect{}
```

pointcut

一个切入点可以通过一个普通的方法定义来提供，并且切入点表达式使用@Pointcut注解，方法返回类型必须为void

定义一个名为‘anyOldTransfer’，这个切入点将匹配任何名为‘transfer’的方法执行

```
@Pointcut("execution(* transfer(..))")//这是pointcut的例子
private void anyOldTransfer(){}
```

Before advice

```
//AspectJ的切入点
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Pointcut("execution(* com.wind.spring.springaop.aspectj.*Asp.*(..)))")
    public void pointcut(){}
    
    //在指定包下的方法前执行
//    @Before("execution(* com.wind.spring.springaop.aspectj.*Asp.*(..))")
    //使用共享的名称
    @Before("pointcut()")
    public void before(){
        System.out.println("AspectJ before");
    }
}
```

After returning advice

@AfterReturning

```
@AfterReturning(pointcut = "pointcut()",returning = "arg")
    public void afterReturning(Object arg){
        System.out.println("AspectJ afterReturning:"+arg);
    }
```



After throwing advice

```
 @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(RuntimeException ex) {
        System.out.println("AspectJ afterThrowing : " + ex.getMessage());
    }
```

After (finally) advice

最终通知必须准备处理正常和异常两种返回情况，他通常用于释放资源

```
@After("pointcut()")
    public void finallyAdvice(){
        System.out.println("AspectJ finallyAdvice");
    }
```

Around advice

环绕通知使用@Around来声明，通知方法的第一个尝试必须是ProceedingJoinPoint类型

```
//AspectJ的环绕通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp){
        Object obj = null;
        try {
            System.out.println("AspectJ around 1");
            obj = pjp.proceed();
            System.out.println("AspectJ around 2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
```

advice的扩展

```
//advice带参数的扩展
    @Around("pointcut() && args(arg)")
    public void beforeWithParam(String arg) {
        System.out.println("AspectJ Before:" + arg);
    }

```

