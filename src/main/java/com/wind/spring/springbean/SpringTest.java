package com.wind.spring.springbean;

import com.wind.spring.springbean.autodiscovery.RequiredMovieLister;
import com.wind.spring.springbean.autodiscovery.ResourceUse;
import com.wind.spring.springbean.resource.LearnResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class SpringTest {

    public static Object getBean(String beanId) {
        ApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        System.out.println("application的信息:" + context);
        return context.getBean(beanId);
    }

    public static void pl(Object object) {
        System.out.println("bean的信息：" + object);
    }

    public static void main(String args[]) {
//        Person userBean = (Person) getBean("person");
//       pl(userBean);
//        testAuto();
//        testResource();
//        testAutoDiscovery();
//        testAutoNameAndScope();
//        testRequired();
//        testBean();
//        testBeanG();
//        testResourceUse();
    }

    /*
     * 自动装配类中的成员变量
     * */
    private static void testAuto() {
        pl(getBean("autobean"));
    }

    /*
     * Resources
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

    /*
     * 自动注册bean到applicationcontext中
     * */
    private static void testAutoDiscovery() {
        pl(getBean("simplemovielister"));
    }

    /*
     * 自动注册bean，并用注解指定bean的id和作用域
     * */
    private static void testAutoNameAndScope() {
        pl(getBean("moviefinderimpl"));
    }

    /*
     * @Required和@Qualifier和@Autowired
     * */
    private static void testRequired() {
        RequiredMovieLister requiredMovieLister = (RequiredMovieLister) getBean("requiredMovieLister");
        pl(requiredMovieLister.getMovieFinder().getClass().getName());
    }

    /*
     * @Bean的使用
     * */
    private static void testBean() {
        pl(getBean("myBook"));
    }

    /*
     * 基于泛型的自动装配
     * */
    private static void testBeanG() {
        getBean("storeTest");
    }

    /*
     * @Resource使用
     *
     * */
    private static void testResourceUse() {
        ResourceUse resourceUse = (ResourceUse) getBean("resourceUse");
        System.out.println(resourceUse.movieFinder.getClass().getSimpleName());
    }


}
