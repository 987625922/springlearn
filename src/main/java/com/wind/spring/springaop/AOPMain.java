package com.wind.spring.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {

    public static Object getBean(String beanId) {
        ApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring/application-aop.xml");
        System.out.println("application的信息:" + context);
        return context.getBean(beanId);
    }

    public static void pl(Object object) {
        System.out.println("bean的信息：" + object);
    }


    public static void main(String args[]) {
        AspectBiz aspectBiz = (AspectBiz) getBean("aspectBiz");
        aspectBiz.biz();
    }
}
