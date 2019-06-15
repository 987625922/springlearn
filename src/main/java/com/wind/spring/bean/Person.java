package com.wind.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@AllArgsConstructor
//自动注入xml bean配置
public class Person {
    String name;
    String number;

    public void print() {
        System.out.println("name：" + name);
    }
}
