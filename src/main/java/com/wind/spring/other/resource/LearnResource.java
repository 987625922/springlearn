package com.wind.spring.other.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Resources
 * spring 对文件读取的支持
 */
@Slf4j
public class LearnResource {

    private static ApplicationContext context;


    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("spring/application-bean.xml");
        resource();
    }

    /**
     * classpath:的使用
     * */
    public static void resource() {
        Resource resource = context.getResource("classpath:spring-resources-learn.txt");
        System.out.println("文件名字：" + resource.getFilename());
        try {
            System.out.println("文件长度：" + resource.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
