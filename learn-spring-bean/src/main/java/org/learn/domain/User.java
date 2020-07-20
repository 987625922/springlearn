package org.learn.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.learn.common.bean.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

/**
 *
 */
@Setter
@Getter
@ToString
@Slf4j
public class User implements BeanFactoryAware,
        BeanNameAware, InitializingBean, DisposableBean {

    private String userName;
    private Book book;
    private String city;

    private String beanName;
    private BeanFactory beanFactory;

    public User() {
        log.debug("User 调用User()空的构造函数");
    }

    public User(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 通过<bean>的init-method属性指定初始化
     */
    public void init() {
        System.out.println("User bean的初始化");
    }

    /**
     * 通过<bean>的destroy-method属性指定初始化
     */
    public void myDestroy() {
        System.out.println("User bean的销毁");
    }

    /**
     * DisposableBean的destroy
     */
    @Override
    public void destroy() {
        System.out.println("User bean的销毁");
    }

    /**
     * BeanFactoryAware接口方法
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.debug("User 调用BeanFactoryAware.setBeanFactory()");
        this.beanFactory = beanFactory;
    }

    /**
     * InitializingBean接口方法
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("User 调用InitializingBean.afterPropertiesSet");
    }

    /***
     * BeanNameAware接口方法
     * @param beanName
     */
    @Override
    public void setBeanName(String beanName) {
        log.debug("User BeanNameAware的BeanName：" + beanName);
    }


}
