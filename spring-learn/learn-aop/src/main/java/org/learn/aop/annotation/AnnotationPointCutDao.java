package org.learn.aop.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


/**
 * 基于注解的aop的目标类
 */
@Repository
@Slf4j
public class AnnotationPointCutDao {


    /**
     * 基于注解的aop的目标方法
     */
    public void save() {
        log.info("基于注解的aop使用");
    }

}
