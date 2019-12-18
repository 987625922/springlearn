package com.wind.spring.springbean.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
 * 得到ioc容器的applicationcontext，
 * 在容器初始化时可对applicationcontext进行操作
 * */
public class MoocApplicationContext implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBean("moocApplicationContext"));
    }
}
