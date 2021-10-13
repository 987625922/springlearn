package org.learn.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 主应用配置
 */
@Configuration
@Import({MvcConfiguration.class, DubboConfiguration.class})
public class AppConfiguration {

}
