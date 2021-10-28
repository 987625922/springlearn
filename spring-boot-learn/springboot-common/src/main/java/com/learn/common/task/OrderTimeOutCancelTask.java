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
        log.info("取消订单，并根据sku编号释放锁定库存");
    }
}
