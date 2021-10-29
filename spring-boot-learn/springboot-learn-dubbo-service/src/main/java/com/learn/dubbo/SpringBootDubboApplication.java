package com.learn.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo //开启Dubbo的注解支持
@SpringBootApplication
public class SpringBootDubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboApplication.class);
    }
}
