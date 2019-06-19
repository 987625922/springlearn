package com.wind.spring.springbean.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
        ApplicationContext ac = getApplication();
        BookDao bookDao = (BookDao) ac.getBean("bookDao");
        bookDao.select();
    }

    private static ApplicationContext getApplication() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");

        System.out.println("application:" + ac);

        return ac;
    }


}
