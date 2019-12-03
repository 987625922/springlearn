package com.wind.spring.mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring 配合mybatis的使用
 *
 */
public class BookDaoMain {

    public static void main(String args[]) {
        ApplicationContext ac = getApplication();

        BookDaoService bookDaoService = (BookDaoService) ac.getBean("bookDaoService");

        try {
            bookDaoService.testQueryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取spring的配置文件，生成spring的applicationContext
     *
     * @return ApplicationContext spring上下文
     */
    private static ApplicationContext getApplication() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring/application-jdbc.xml");
        System.out.println("application:" + ac);
        return ac;
    }

}
