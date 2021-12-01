package com.learn.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//开启异步调用 配合 @Async 使用
@SpringBootApplication
public class SpringCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCommonApplication.class);
    }
}
