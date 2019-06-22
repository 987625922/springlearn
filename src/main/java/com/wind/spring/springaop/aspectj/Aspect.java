package com.wind.spring.springaop.aspectj;

import org.aspectj.lang.annotation.Pointcut;

//AspectJ的切入点
public class Aspect {

    @Pointcut("execution(*com.wind.spring.springaop.aspectj.*Biz.*(..))")
    public void pointcut(){

    }

    @Pointcut("within(com.wind.spring.springaop.aspectj.*)")
    public void bizPointcut(){

    }
}
