package com.wind.spring.spring.annotationbean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//11.@ImportResource和@Value注解
@Configuration //声明为配置文件
//@ImportResource("classpath:jdbc.properties") //导入资源文件
public class AppDataSourceConfig {

    @Value("${jdbc.driver}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String user;
    @Value("${jdbc.password}")
    private String password;

    @Bean //声明bean对象到xml
    public DataSource dataSource() {
        return new DriverManagerDataSource(jdbcUrl, user, password);
    }
}
