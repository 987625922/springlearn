package com.wind.spring.databases.springjdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring 自带对数据库的jdbc封装的springjdbc的使用和对spring事务的使用
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-jdbc.xml"})
public class JdbcTemplateTest {

    @Autowired
    private BookTxDao bookTxDao;

    @Test
    public void test() {

        //xml实现事务
        bookTxDao.update();
        //注解方式实现事务
        bookTxDao.upTxdate();

        bookTxDao.select();
    }

}
