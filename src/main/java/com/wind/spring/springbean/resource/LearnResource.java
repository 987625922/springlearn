package com.wind.spring.springbean.resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * Resources
 * spring 对文件读取的支持
 */
public class LearnResource implements ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public void resource() throws IOException {
        Resource resource = context.getResource("classpath:config.txt");
        System.out.println("文件名字：" + resource.getFilename());
        System.out.println("文件长度：" + resource.contentLength());
    }

}
