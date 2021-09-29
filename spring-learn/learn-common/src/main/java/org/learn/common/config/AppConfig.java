package org.learn.common.config;

import org.learn.common.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class AppConfig {
    /**
     * @Bean就相当于如下 ,@Bean可以去指定bean的name,initMethod和destroyMethod
     * 使用initMethod和destroyMethod注册生命周期
     * <bean id="myBook" class="com.wind.springbean.bean.BookJpa"/>
     * @Scope 对象在spring容器(IOC容器)中的生命周期
     */
    @Bean(name = "myBook", initMethod = "init", destroyMethod = "cleanup")
    @Scope(value = "prototype")
    public Book myBook() {
        return new Book();
    }
}
