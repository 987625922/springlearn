package com.learn.rabbitmq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基于erlang开发，所以并发能力很强，性能极其好，延时很低;管理界面较丰富
 * 消息队列推荐使用
 */
@SpringBootApplication
public class SpringRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitMqApplication.class, args);
    }
}
