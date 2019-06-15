package com.wind.spring.spring;

import com.wind.spring.bean.Book;
import com.wind.spring.bean.Person;
import com.wind.spring.bean.UserBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        Person userBean = (Person) getBean("person");
       pl(userBean);
    }
}
