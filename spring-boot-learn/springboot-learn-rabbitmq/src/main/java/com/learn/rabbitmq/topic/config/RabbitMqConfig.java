package com.learn.rabbitmq.topic.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 消息队列配置
 *  为mq创建两条队列
 *  topic 是 RabbitMQ 中最灵活的一种方式，可以根据 routing_key 自由的绑定不同的队列
 */
@Configuration
public class RabbitMqConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "item_topic_exchange";

    // 队列名称
    public static final String ITEM_QUEUE = "item_queue";

    // 第二条队列名称
    public static final String ITEM_QUEUE_TWO = "item_queue_two";

    //声明队列
    @Bean("itemQueue")
    public Queue itemQueue(){
        return QueueBuilder.durable(ITEM_QUEUE).build();
    }

    //声明第二条队列
    @Bean("itemQueueTwo")
    public Queue itemQueueTwo(){
        return QueueBuilder.durable(ITEM_QUEUE_TWO).build();
    }

    //声明交换机
    @Bean("itemTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    //绑定队列和交换机
    @Bean
    public Binding itemQueueExchange(@Qualifier("itemQueue") Queue itemQueue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(itemQueue).to(exchange).with("item.#").noargs();
    }

    //绑定第二条队列和交换机
    @Bean
    public Binding itemQueueExchangeTwo(@Qualifier("itemQueueTwo") Queue itemQueueTwo,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(itemQueueTwo).to(exchange).with("topic.message").noargs();
    }


    /**
     * 实际在这两个监听里面去做重发并不是很多，因为成本太高了，首先 RabbitMQ 本身丢失的可能性就非常低，
     * 其次如果这里需要落库再用定时任务扫描重发还要开发一堆代码，分布式定时任务…再其次定时任务扫描肯定会增加消息延迟，
     * 不是很有必要。真实业务场景是记录一下日志就行了，方便问题回溯，顺便发个邮件给相关人员，
     * 如果真的极其罕见的是生产者弄丢消息，那么开发往数据库补数据就行了。
     *
     * 生产者发送消息失败回调
     */
    @PostConstruct
    public void enableConfirmCallback() {
        //confirm 监听，当消息成功发到交换机 ack = true，没有发送到交换机 ack = false
        //correlationData 可在发送时指定消息唯一 id
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(!ack){
                //记录日志、发送邮件通知、落库定时任务扫描重发
            }
        });

//        //当消息成功发送到交换机没有路由到队列触发此监听
//        rabbitTemplate.setReturnsCallback(returned -> {
//            //记录日志、发送邮件通知、落库定时任务扫描重发
//        });
    }
}
