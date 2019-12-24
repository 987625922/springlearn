package com.wind.spring.springaop.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 基于注解的aop的目标类
 */
@Component
public class AnnotationPointCutDao {

    private Logger logger = LoggerFactory.getLogger(AnnotationPointCutDao.class);

    /**
     * 基于注解的aop的目标方法
     */
    public void save() {
        logger.debug("基于注解的aop使用");
    }

}
