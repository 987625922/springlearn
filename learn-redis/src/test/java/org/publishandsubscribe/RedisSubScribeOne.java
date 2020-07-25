package org.publishandsubscribe;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.time.LocalDateTime;

/**
 * @Author: 98762
 * @Date: 2020/7/23 20:48
 * @Description: redis发布订阅模式 订阅者
 * @备注： 1.
 */
@Slf4j
public class RedisSubScribeOne extends JedisPubSub {

    /**
     * 当收到订阅的时候，这个方法会自动回调
     *
     * @param channel 频道名称
     * @param message 发布的消息
     */
    @Override
    public void onMessage(String channel, String message) {
        log.info("订阅者：订阅频道【" + channel + "】,收到的消息是【" + message
                + "】，时间为：【" + LocalDateTime.now().toString() + "】");
    }

    /**
     * 订阅频道
     *
     * @param args
     */
    public static void main(String[] args) {
        log.info("订阅者启动...");
        //创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //创建订阅者
        RedisSubScribeOne one = new RedisSubScribeOne();
        //订阅频道
        jedis.subscribe(one, "cctv5");
    }
}
