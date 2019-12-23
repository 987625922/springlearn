package com.wind.spring.springbean;

import com.wind.spring.bean.User;
import com.wind.spring.springbean.autodiscovery.RequiredMovieLister;
import com.wind.spring.springbean.resource.LearnResource;
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

    public static Object getBean(String beanId) {
        ApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring/application-bean.xml");
        System.out.println("application的信息:" + context);
        return context.getBean(beanId);
    }

    public static void pl(Object object) {
        System.out.println("bean的信息：" + object);
    }

    public static void main(String args[]) {
        User userBean = (User) getBean("userbean");
        logger.debug("SpringTest中获取到UserBean：" + userBean.toString());

        testAuto();
        testResource();
        testRequired();
    }

    /*
     * 自动装配类中的成员变量
     * */
    private static void testAuto() {
        pl(getBean("autobean"));
    }

    /**
     * Resources
     * spring对读取文件的支持
     * */
    private static void testResource() {
        LearnResource resource = (LearnResource) getBean("learnresource");
        pl(resource);
        try {
            resource.resource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Required,@PostConstruct和@Qualifier和@Autowired的使用
     * */
    private static void testRequired() {
        RequiredMovieLister requiredMovieLister = (RequiredMovieLister) getBean("requiredMovieLister");
        pl(requiredMovieLister.getMovieFinder().getClass().getName());
    }



}
