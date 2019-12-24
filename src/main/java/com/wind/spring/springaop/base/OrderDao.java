package com.wind.spring.springaop.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 没有接口的类使用spring的aop，spring会用cglib对方法进行aop
 */
@Component("order")
public class OrderDao {

    private Logger logger = LoggerFactory.getLogger(OrderDao.class);

    public void save() {
        logger.debug("CGLIB AOP的切点方法 OrderDao类的save方法");
    }
}
