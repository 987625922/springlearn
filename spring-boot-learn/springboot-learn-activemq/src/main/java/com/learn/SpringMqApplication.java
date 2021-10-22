package com.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan //todo 扫描代码中的@WebServlet @WebFilter @WebListener 注解自动注册（servlet 3.0功能）
@MapperScan("com.wind.springbootlearn2.mapper")//todo 扫描mapper下面的类，开启mybatis的mapper使用
@EnableScheduling //todo 开启定时任务，扫描定时任务
@EnableAsync //todo 开启一个异步任务
@EnableJms //todo @EnableJms，开启支持jms activeMQ消息队列需要使用
/**
 * 成熟的产品，在很多公司得到应用；有较多的文档；各种协议支持较好
 * 但是并发不如RabbitMQ
 * 社区也没有RabbitMQ活跃
 */
public class SpringMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMqApplication.class, args);
    }
}
