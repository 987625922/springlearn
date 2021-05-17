package org.publishandsubscribe;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Author: 98762
 * @Date: 2020/7/23 21:00
 * @Description: redis发布订阅模式 发布者
 * @备注： 1.
 */
@Slf4j
public class RedisPublishOne {

    /**
     * 发布消息
     *
     * @param args
     */
    public static void main(String[] args) {
      log.info("开始发布消息");
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.publish("cctv5","RedisPublishOne发布的消息内容");
        log.info("消息发布完毕");
    }
}
