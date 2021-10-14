package com.learn;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@ServletComponentScan //todo 扫描代码中的@WebServlet @WebFilter @WebListener 注解自动注册（servlet 3.0功能）
@MapperScan("com.wind.springbootlearn2.mapper")//todo 扫描mapper下面的类，开启mybatis的mapper使用
@EnableScheduling //todo 开启定时任务，扫描定时任务
@EnableAsync //todo 开启一个异步任务
@EnableJms //todo @EnableJms，开启支持jms activeMQ消息队列需要使用
public class SpringMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMqApplication.class, args);
    }

    /**
     * 把ActiveMQ的queue对象交给spring进行管理，使用时只需要注入就可以了
     * 可以不这样写
     * 创建点对点消息队列的queue
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("wind-queues");
    }

    /**
     * 创建activemq消息发布订阅模型的topic
     * 把topic交给spring进行管理，避免每一次都new一个
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("video.topic");
    }

    /**
     * 使activemq支持发布订阅模型，需要在每一个订阅者的方法@JmsListener的注解中添加,containerFactory = "jmsListenerContainerTopic"
     * activemq默认只支持一个发布模型（要么点对点，要么发布订阅）
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
