package com.wind.spring.springaop;

import org.springframework.stereotype.Component;

//切面的类
public class Aspect {
    public void before(){
        System.out.println("aspect的before使用");
    }
    public void afterReturning(){
        System.out.println("aspect的afterReturning的使用");
    }
}
