package com.wind.spring.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookDaoMain {

    public static void main(String args[]) {
        ApplicationContext ac = getApplication();

        BookDaoService bookDaoService = (BookDaoService) ac.getBean("bookDaoService");

        try {
            bookDaoService.testQueryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static ApplicationContext getApplication() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring/application-jdbc.xml");
        System.out.println("application:" + ac);
        return ac;
    }

}
