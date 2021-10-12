package org.learn.common.resource;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Resources
 * spring 对文件读取的支持
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-other.xml"})
public class LearnResource {

    @Autowired
    ApplicationContext context;
    /**
     * classpath:的使用
     * */
    @Test
    public void resource1() {
        Resource resource = context.getResource("classpath:spring-resources-learn.txt");
        System.out.println("文件名字：" + resource.getFilename());
        try {
            System.out.println("文件长度：" + resource.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
