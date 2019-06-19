package com.wind.spring.springbean.annotationbean;

import com.wind.spring.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Bean and @Scope
@Configuration
public class BeanAndScope {
    @Bean
    @Scope(value = "prototype")
    public Book scopeBook(){
        return new Book();
    }
}
