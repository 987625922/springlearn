package com.wind.spring.springaop;

import com.wind.spring.springaop.base.IUserDao;
import com.wind.spring.springaop.base.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 基于spring xml配置的aop使用
 * 主要用于实现事务、缓存、安全等功能
 */
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-aop.xml"})
public class SpringAopTest {

    @Autowired
    OrderDao orderDao;

    @Autowired
    IUserDao userDao;

    @Test
    public void test() {
        //spring aop对于cglib的使用
        orderDao.save();

        //spring aop对于jdk的aop使用
        //com.sun.proxy.$Proxy21 cannot be cast to
        //不能用接口的实现类（Computerimpl）来转换Proxy的实现类，它们是同级，应该用共同的接口来转换。
        userDao.save();
    }


}
