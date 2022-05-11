package com.learn.rabbitmq.controller;

import com.learn.rabbitmq.topic.config.RabbitMqConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MqController
 * rabbitmq 一个发送者，N个接受者,经过测试会均匀的将消息发送到N个接收者中
 * 一对多一样，接收端仍然会均匀接收到消息
 */
@Controller
@RequestMapping("/order")
public class MqController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 普通的消息队列发送
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    @ResponseBody
    public void send() {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(RabbitMqConfig.ITEM_TOPIC_EXCHANGE,
                RabbitMqConfig.ITEM_QUEUE, "发送的消息",
                message -> {
                    //给消息设置延迟毫秒值
                    message.getMessageProperties().setExpiration(String.valueOf(1000));
                    return message;
                });

    }

    /**
     * 第二条消息队列发送
     */
    @RequestMapping(value = "/send2", method = RequestMethod.GET)
    @ResponseBody
    public void send2() {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(RabbitMqConfig.ITEM_TOPIC_EXCHANGE,
                RabbitMqConfig.ITEM_QUEUE_TWO, "发送第二条消息队列的消息");
    }

    /**
     * 发送广播消息（Fanout）
     */
    @RequestMapping(value = "/sendFanout", method = RequestMethod.GET)
    @ResponseBody
    public void sendFanout() {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(RabbitMqConfig.ITEM_TOPIC_EXCHANGE,
                RabbitMqConfig.ITEM_QUEUE_TWO, "发送广播消息");
    }
}
