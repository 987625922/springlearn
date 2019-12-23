package com.wind.spring.springbean.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;

/**
 *如果某个bean需要访问配置文件中本身bean的id属性，这个bean类通过实现BeanNameAware接口，
 * 在依赖关系确定之后，初始化方法之前，提供回调自身的能力，从而获得本身bean的id属性，该接口提供了
 * void setBeanName(String name)方法，
 * 需要指出的时该方法的name参数就是该bean的id属性。回调该setBeanName方法可以让bean获取自身的id属性
 */
public class MoocBeanNameAware implements BeanNameAware {

    private static Logger logger = LoggerFactory.getLogger(MoocBeanNameAware.class);

    @Override
    public void setBeanName(String name) {
        logger.debug("MoocBeanNameAware的使用："+name);
    }

}
