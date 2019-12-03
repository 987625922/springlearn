package com.wind.spring.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * spring 自带对数据库的jdbc封装
 */
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String args[]) {
        ApplicationContext ac = getApplication();
        //spring jdbc的使用
//        BookDao bookDao = (BookDao) ac.getBean("bookDao");
//        bookDao.select();
        BookTxDao bookTxDao = (BookTxDao) ac.getBean("bookTxDao");
        //xml实现事务
        bookTxDao.update();
        //注解方式实现事务
        bookTxDao.upTxdate();
    }

    private static ApplicationContext getApplication() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring/application-jdbc.xml");

        System.out.println("application:" + ac);

        return ac;
    }
}
