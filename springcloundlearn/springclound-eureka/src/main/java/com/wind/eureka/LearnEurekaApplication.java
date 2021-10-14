package com.wind.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//设置为eureka服务器
@EnableEurekaServer
public class LearnEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnEurekaApplication.class, args);
    }
}
