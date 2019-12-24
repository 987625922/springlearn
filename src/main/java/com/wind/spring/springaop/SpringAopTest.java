package com.wind.spring.springaop;

import com.wind.spring.springaop.base.IUserDao;
import com.wind.spring.springaop.base.OrderDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 基于spring xml配置的aop使用
 * 主要用于实现事务、缓存、安全等功能
 */
public class SpringAopTest {

    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("spring/application-bean.xml");


    public static void main(String[] args) {
        //spring aop对于cglib的使用
        OrderDao orderDao = (OrderDao) context.getBean("order");
        orderDao.save();

        //spring aop对于jdk的aop使用
        //com.sun.proxy.$Proxy21 cannot be cast to
        //不能用接口的实现类（Computerimpl）来转换Proxy的实现类，它们是同级，应该用共同的接口来转换。
        IUserDao userDao = (IUserDao) context.getBean("userDao");
        userDao.save();
    }


}
