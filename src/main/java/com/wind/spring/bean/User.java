package com.wind.spring.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String userName;
    private Book book;
    private String beanName;

    public User() {

    }

    public User(String beanName) {
        this.beanName = beanName;
    }

    public void init() {
        System.out.println("bean的初始化");
    }

    public void destroy() {
        System.out.println("bean的销毁");
    }
}
