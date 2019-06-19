package com.wind.spring.springbean.annotationbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//基于泛型的自动装配
@Configuration
public class BeanG {

    @Bean(name = "storeTest")
    public Store storeTest(){
        System.out.println("s1："+s1.getClass().getName());
        System.out.println("s2："+s2.getClass().getName());
        return new StringStore();
    }

    @Autowired //基于泛型的自动装配
    private Store<String> s1;
    @Autowired //基于泛型的自动装配
    private Store<Integer> s2;

    @Bean
    public StringStore stringStore() {
        return new StringStore();
    }

    @Bean
    public IntegerStore integerStore() {
        return new IntegerStore();
    }

    @Component
    class IntegerStore implements Store<Integer> {

    }

    @Component
    class StringStore implements Store<String> {

    }

    interface Store<T> {

    }
}
