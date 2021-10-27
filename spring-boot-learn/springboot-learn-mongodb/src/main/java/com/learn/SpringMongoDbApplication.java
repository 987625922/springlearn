package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Mongodb是为快速开发互联网Web应用而构建的数据库系统，
 * 其数据模型和持久化策略就是为了构建高读/写吞吐量和高自动灾备伸缩性的系统。
 */
@SpringBootApplication
public class SpringMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoDbApplication.class);
    }
}
