package com.wind.spring.databases.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-redis.xml"})
public class RedisTest {
    @Autowired
    JedisCacheClient jedisCacheClient;

    /**
     * redis set和get
     */
    @Test
    public void test(){
        jedisCacheClient.set("sign", "value为redis测试数据，key为sign");
        log.debug(jedisCacheClient.get("sign"));

    }


}
