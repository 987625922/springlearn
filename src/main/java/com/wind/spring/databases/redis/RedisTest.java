package com.wind.spring.databases.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * redis的基本使用
 */
public class RedisTest {

    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("spring/application-redis.xml");

    public static void main(String[] args) {
        JedisCacheClient jedisCacheClient = (JedisCacheClient) context.getBean("jedisCacheClient");
        jedisCacheClient.set("sign", "value为redis测试数据，key为sign");
        logger.debug(jedisCacheClient.get("sign"));
    }


}
