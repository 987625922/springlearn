package com.wind.spring.springaop.api;


import java.lang.reflect.Method;

public class AfterReturningAdvice implements org.springframework.aop.AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] objects, Object target) throws Throwable {
        System.out.println("afterReturningAdvice："+method.getName()+"  "+target.getClass().getName()+" "+
        returnValue);
    }
}
