package com.wind.spring.bean;

import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Setter
//自动注入xml bean配置
@Component
public class Person {
    String name;
    String number;

    public void print() {
        System.out.println("name：" + name);
    }
}
