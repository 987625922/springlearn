package com.learn.common.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务配置
 * SpringTask是Spring自主研发的轻量级定时任务工具，
 * 相比于Quartz更加简单方便，且不需要引入其他依赖即可使用。
 */
@Configuration
@EnableScheduling//开启SpringTask的定时任务能力
public class SpringTaskConfig {
}
