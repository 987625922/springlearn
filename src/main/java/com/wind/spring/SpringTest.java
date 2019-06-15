package com.wind.spring;

import com.wind.spring.bean.Person;
import com.wind.spring.bean.UserBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String args[]) {
        ApplicationContext ac = getApplication();
        UserBean userBean = (UserBean) ac.getBean("userbean");
        userBean.setPerson(new Person("测试name","测试number"));
        System.out.println(userBean);
    }


    private static ApplicationContext getApplication() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring/applicationContext.xml");

        System.out.println("application:"+ac);

        return ac;
    }
}
