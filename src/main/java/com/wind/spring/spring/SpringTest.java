package com.wind.spring.spring;

import com.wind.spring.spring.resource.LearnResource;
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
        testRequired();
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
    * required
    * */
    private static void testRequired(){
        pl(getBean("requiredMovieLister"));
    }

}
