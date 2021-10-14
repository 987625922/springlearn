package com.wind.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//开启Feign的功能
@EnableFeignClients(basePackages = {"com.wind.common.api"})
public class LearnWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnWebApplication.class, args);
    }
}
