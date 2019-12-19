package com.wind.spring.springaop;

//切面的类
public class Aspect {
    //在方法之前调用
    public void before() {
        System.out.println("aspect的before使用");
    }

    //在方法之后调用
    public void afterReturning() {
        System.out.println("aspect的afterReturning的使用");
    }

    //在方法抛出异常执行
    public void afterThrowing() {
        System.out.println("aspect的afterThrowing的使用");
    }

    //在afterReturning之后执行，也被称为final的advice
    public void after() {
        System.out.println("aspect的after的使用");
    }
}
