package com.wind.spring.springbean.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring中提供了一些以Aware结尾的接口，实现了Aware接口的bean在初始化之后，
 * 可以获取相应资源，通过Aware接口，可以对Spring相应资源进行操作（一定要慎重 ）
 * ，为对Spring进行简单的扩展提供了方便的入口
 * <p>
 * 得到ioc容器的applicationcontext，
 * 在容器初始化时可对applicationcontext进行操作
 */
public class MoocApplicationContext implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(MoocApplicationContext.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("获取到的：" + applicationContext.getBean("moocApplicationContext") + "可以通过他对application的" +
                "初始化进行一些操作");
    }
}
