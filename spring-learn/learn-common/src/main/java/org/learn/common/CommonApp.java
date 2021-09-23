package org.learn.common;


import org.learn.common.bean.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//spring最基础的启动方式
public class CommonApp {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/application-base.xml");
        Book obj = (Book) context.getBean("bookBean");
        System.out.println(obj.toString());
    }

}
