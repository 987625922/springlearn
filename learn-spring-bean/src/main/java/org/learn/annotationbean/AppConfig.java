package org.learn.annotationbean;

import org.learn.common.bean.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * @Configuration 用于定义配置类，可替换xml配置文件，被注解的类内部包含有
 * 一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext
 * 或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
 */
@Configuration
@PropertySource("classpath:jdbc.properties") //导入资源文件
public class AppConfig {

    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    //获取配置文件的
    @Value("${jdbc.driver}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String user;
    @Value("${jdbc.password}")
    private String password;


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


    /**
     * 通过配置类的方式获取到数据库的DataSource
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(jdbcUrl, user, password);
    }


    @Bean(name = "storeTest")
    public Store storeTest() {
        logger.debug("自动装填到的s1:" + s1.getClass().getName());
        logger.debug("自动装填到的s2:" + s2.getClass().getName());
        return new StringStore();
    }

    /**
     * 基于泛型的自动装配
     */
    @Autowired //基于泛型的自动装配
    private Store<String> s1;
    @Autowired //基于泛型的自动装配
    private Store<Integer> s2;

    @Bean
    public StringStore stringStore() {
        return new StringStore();
    }

    @Bean
    public IntegerStore integerStore() {
        return new IntegerStore();
    }

    @Component
    class IntegerStore implements Store<Integer> {

        @Override
        public void print() {
            System.out.println("这个是IntegerStore");
        }
    }

    @Component
    class StringStore implements Store<String> {

        @Override
        public void print() {
            System.out.println("这个是StringStore");
        }
    }

    public interface Store<T> {
        void print();
    }

}
