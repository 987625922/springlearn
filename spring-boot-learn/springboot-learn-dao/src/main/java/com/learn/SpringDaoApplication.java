package com.learn;

import com.learn.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@MapperScan("com.learn.dao")//todo 扫描mapper下面的类，开启mybatis的mapper使用
@SpringBootApplication
public class SpringDaoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDaoApplication.class, args);
        BookService bookService = (BookService) context.getBean("bookService");
        log.info("selectList ==>", bookService.selectById(2));
    }
}
