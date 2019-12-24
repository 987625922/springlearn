package com.wind.spring.springaop;

import com.wind.spring.springaop.annotation.AnnotationPointCutDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于注解的aop使用
 */
public class SpringAnnotationAOPTest {

    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("spring/application-bean.xml");

    public static void main(String[] args) {
        AnnotationPointCutDao aop = (AnnotationPointCutDao) context.getBean("annotationPointCutDao");
        aop.save();

    }
}
