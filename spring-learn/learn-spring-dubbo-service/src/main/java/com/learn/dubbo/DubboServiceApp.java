package com.learn.dubbo;

import com.learn.dubbo.config.DubboConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * 启动dubbo服务端
 */
public class DubboServiceApp {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(DubboConfiguration.class);
        context.start();
        //让程序不运行结束
        System.out.println("DubboServiceApp is running. ================>");
        System.in.read();
    }
}
