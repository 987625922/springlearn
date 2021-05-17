package com.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 主应用配置
 */
@Configuration
@ComponentScan(basePackages = {"com.learn.web"})
@Import({MvcConfiguration.class,DubboConfiguration.class})
public class AppConfiguration {

}
