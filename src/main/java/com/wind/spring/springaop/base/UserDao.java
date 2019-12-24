package com.wind.spring.springaop.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 主要测试spring中，对于有接口的类的aop
 * 用jdk中的aop方法实现
 */
@Component
public class UserDao implements IUserDao {

    private Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Override
    public void save() {
        int i = 1/0;
        logger.debug("JDK AOP的切点方法 UserDao类的save方法");
    }
}
