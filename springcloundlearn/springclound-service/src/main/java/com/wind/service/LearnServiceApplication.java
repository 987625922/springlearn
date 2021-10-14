package com.wind.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.wind.common.api"})
@EnableHystrix
public class LearnServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnServiceApplication.class, args);
    }
}
