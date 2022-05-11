package com.learn.rabbitmq.topic.receiver;

import com.learn.rabbitmq.topic.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbitmq消费者
 * 监听第一条item_queue的消息队列
 */
@Component
@RabbitListener(queues = RabbitMqConfig.ITEM_QUEUE)
public class MqReceiver {

    @RabbitHandler
    public void msg(String msg){
        System.out.println("消费者运行："+msg);
    }
}
