package com.wind.spring.springaop.aspectj;

import org.springframework.stereotype.Component;

@Component
public class AspectJAsp {

    public String test(String arg){
        System.out.println("AspectJAsp:"+arg);
//        throw new RuntimeException("test failed!");
        return arg;
    }

}
