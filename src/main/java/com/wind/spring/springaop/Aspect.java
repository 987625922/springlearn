package com.wind.spring.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

//切面的类
public class Aspect {
    //在方法之前调用
    public void before(){
        System.out.println("aspect的before使用");
    }
    //在方法之后调用
    public void afterReturning(){
        System.out.println("aspect的afterReturning的使用");
    }
    //在方法抛出异常执行
    public void afterThrowing(){
        System.out.println("aspect的afterThrowing的使用");
    }
    //在afterReturning之后执行，也被称为final的advice
    public void after(){
        System.out.println("aspect的after的使用");
    }
    //环绕通知
    public Object around(ProceedingJoinPoint pjp){
        Object object = null;
        try {
            System.out.println("aspect的around的使用.1");
            object = pjp.proceed();
            System.out.println("aspect的around的使用.2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
    //环绕通知带参数
    public Object aroundInit(ProceedingJoinPoint pjp,String bizName,int times){
        System.out.println(bizName+" "+times);
        Object object = null;
        try {
            System.out.println("aspect的around的使用.1");
            object = pjp.proceed(); //切点方法的执行
            System.out.println("aspect的around的使用.2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
}
