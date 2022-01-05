package com.learn.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 * 处理超时未支付的订单的定时器
 */
@Slf4j
@Component
public class OrderTimeOutCancelTask {

    @Scheduled(cron = "0 0/1 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO: 此处应调用取消订单的方法
        log.info("定时任务，每分钟一次执行一次");
    }
}
