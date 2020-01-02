package com.wind.spring.springbean;

import com.wind.spring.bean.User;
import com.wind.spring.springbean.autodiscovery.RequiredMovieLister;
import com.wind.spring.other.resource.LearnResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * spring中基于application-bean.xml配置文件的bean的使用
 */
public class SpringTest {

    private static Logger logger = LoggerFactory.getLogger(SpringTest.class);

    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("spring/application-bean.xml");

    public static Object getBean(String beanId) {
        logger.debug("application的信息:" + context);
        return context.getBean(beanId);
    }

    public static void main(String args[]) {
        User userBean = (User) getBean("userbean");
        logger.debug("SpringTest中获取到UserBean：" + userBean.toString());

        testAuto();
        testRequired();
    }



    /**
     * 自动装配类中的成员变量
     */
    private static void testAuto() {
        logger.debug(getBean("autobean").toString());
    }



    /**
     * @Required,@PostConstruct和@Qualifier和@Autowired的使用
     */
    private static void testRequired() {
        RequiredMovieLister requiredMovieLister =
                (RequiredMovieLister) getBean("requiredMovieLister");
        logger.debug(requiredMovieLister.getMovieFinder().getClass().getName());
    }


}
