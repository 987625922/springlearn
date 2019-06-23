package com.wind.spring.springaop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//AspectJ的切入点
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    @Pointcut("execution(* com.wind.spring.springaop.aspectj.*Asp.*(..)))")
    public void pointcut() {

    }

    @Pointcut("within(com.wind.spring.springaop.aspectj.*)")
    public void bizPointcut() {

    }

    //在指定包下的方法前执行
//    @Before("execution(* com.wind.spring.springaop.aspectj.*Asp.*(..))")
    //使用共享的名称
    @Before("pointcut()")
    public void before() {
        System.out.println("AspectJ before");
    }

    @AfterReturning(pointcut = "pointcut()", returning = "arg")
    public void afterReturning(Object arg) {
        System.out.println("AspectJ afterReturning:" + arg);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "ex")
    public void afterThrowing(RuntimeException ex) {
        System.out.println("AspectJ afterThrowing : " + ex.getMessage());
    }

    @After("pointcut()")
    public void finallyAdvice() {
        System.out.println("AspectJ finallyAdvice");
    }

    //AspectJ的环绕通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
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

    //advice带参数的扩展
    @Around("pointcut() && args(arg)")
    public void beforeWithParam(String arg) {
        System.out.println("AspectJ Before:" + arg);
    }

}
