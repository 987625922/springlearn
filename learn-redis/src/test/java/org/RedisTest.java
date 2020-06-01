package org;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.redis.JedisCacheClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * redis的直接使用
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/application-redis.xml"})
public class RedisTest {
    @Autowired
    JedisCacheClient jedisCacheClient;

    /**
     * redis set和get
     */
    @Test
    public void test(){
        /**
         * redis命名规则(全部大写)
         * 1)第一段放项目名
         * 2)第二段放表名
         * 3)第三端放用于区分key的字段，对应数据库中的主键列名，如user_id
         * 4)第四段放置主键值,如18,16
         * 结合起来 PRO:USER:USERID:18
         */
        jedisCacheClient.set("XXX:BBB:CCC", "value为redis测试数据，key为sign");
        log.debug(jedisCacheClient.get("sign"));

    }


}
