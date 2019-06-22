package com.wind.spring.springaop.api;

import org.aopalliance.intercept.MethodInvocation;

public class MethodInterceptor implements org.aopalliance.intercept.MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("methodingerceptor 1:" + methodInvocation.getMethod().getName() + " " +
                methodInvocation.getStaticPart().getClass().getName());
        Object obj = methodInvocation.proceed();
        System.out.println("methodingerceptor 2:" + obj);
        return obj;
    }
}
