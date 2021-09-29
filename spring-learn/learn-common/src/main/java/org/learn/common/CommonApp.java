package org.learn.common;


import org.learn.common.bean.Book;
import org.learn.common.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

//spring最基础的启动方式
public class CommonApp {

    public static void main(String[] args) {

//        ApplicationContext context =
//                new ClassPathXmlApplicationContext("classpath:spring/application-base.xml");
//        Book obj = (Book) context.getBean("bookBean");
//        System.out.println(obj.toString());
        List list = new ArrayList();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Book obj = (Book) context.getBean("myBook");
        System.out.println(obj.toString());
    }

}
