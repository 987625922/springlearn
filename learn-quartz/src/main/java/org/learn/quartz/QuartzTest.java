package org.learn.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 */
@Component
public class QuartzTest {

    //定时任务每天0点触发
//    @Scheduled(cron = "59 59 23 * * ?")
    //每隔5秒触发
    @Scheduled(cron = "*/5 * * * * ?")
    public void show() {
        System.out.println(System.currentTimeMillis() + "");
    }
}
