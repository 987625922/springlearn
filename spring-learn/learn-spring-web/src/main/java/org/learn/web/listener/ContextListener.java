package org.learn.web.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听器的使用
 * 使用：
 *     在web.xml中配置
 *     <listener>
 *     <listener-class>listener.ContextListener</listener-class>
 *     </listener>
 * 其他监听器:
 *          ContextAttributeListener
 *          HttpSessionListener
 *          HttpSessionAttributeListener
 *          ServletRequestListener
 *          ServletRequestAttributeListener
 *
 */
@Slf4j
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      log.info("web 应用初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("web 应用销毁");
    }
}
