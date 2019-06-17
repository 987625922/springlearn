package com.wind.spring.spring.annotationbean;

import com.wind.spring.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//10.@Bean
@Configuration
public class AppConfig {
    /*
     *  @Bean就相当于如下 ,@Bean可以去指定bean的name,还可以通过initMethod和destroyMethod注册生命周期
     *  <bean id="myBook" class="com.wind.spring.bean.Book"/>
     * */
    @Bean(name = "myBook",initMethod = "init",destroyMethod = "cleanup")
    public Book myBook() {
        return new Book();
    }
}
