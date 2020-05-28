package org.learn.aop;

import com.wind.spring.springaop.annotation.AnnotationPointCutDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于注解的aop使用
 */
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-aop.xml"})
public class SpringAnnotationAOPTest {

    @Autowired
    AnnotationPointCutDao aop;

    @Test
    public void test() {
        aop.save();
    }
}
