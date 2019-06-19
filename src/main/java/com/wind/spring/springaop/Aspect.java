package com.wind.spring.springaop;

import org.springframework.stereotype.Component;

public class Aspect {
    public void before(){
        System.out.println("aspect的before使用");
    }
}
