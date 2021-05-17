package org.learn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
         ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-quartz.xml");

    }
}
