package com.wind.spring.springbean.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring中提供了一些以Aware结尾的接口，实现了Aware接口的bean在初始化之后，
 * 可以获取相应资源，通过Aware接口，可以对Spring相应资源进行操作（一定要慎重 ）
 * ，为对Spring进行简单的扩展提供了方便的入口
 * <p>
 * 得到ioc容器的applicationcontext，
 * 在容器初始化时可对applicationcontext进行操作
 *
 * =========================================
 *
 * 对于应用程序来说，应该尽量减少对spring api的耦合程度，然后有时候为了运用spring提供的一些功能，
 * 有必要让bean了解spring容器对其管理的细节信息，如让bean知道在容器中是以哪个名称被管理的，
 * 或者让bean知道beanFactory或这applicationcontext的存在，
 * 也就是让该bean可以获取到beanfactory或applicationcontext的实例。
 *
 * 1 BeanNameAware接口
 *
 * 如果某个bean需要访问配置文件中本身bean的id属性，这个bean类通过实现BeanNameAware接口，
 * 在依赖关系确定之后，初始化方法之前，提供回调自身的能力，从而获得本身bean的id属性，
 * 该接口提供了 void setBeanName(String name)方法，需要指出的时该方法的name参数就是该bean的id属性。
 * 回调该setBeanName方法可以让bean获取自身的id属性
 *
 * 2.BeanFactoryAware接口
 *
 *   实现了BeanFactoryAware接口的bean，可以直接通过beanfactory来访问spring的容器，当该bean被容器创建之后，
 *   会有一个相应的beanfactory的实例引用。
 *   该 接口有一个方法void setBeanFactory(BeanFactory beanFactory)方法通过这个方法的参数创建它的BeanFactory实例，
 *   实现了BeanFactoryAware接口，就可以让Bean拥有访问Spring容器的能力。
 *
 * 3 ApplicationContextAware接口
 *
 * 在Bean类被初始化后，将会被注入applicationContext实例，该接口有一个方法，
 * setApplicationContext(ApplicationContext context),使用其参数context用来创建它的applicationContext实例
 *
 *
 *
 */
public class MoocApplicationContext implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(MoocApplicationContext.class);


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("获取到的：" + applicationContext.getBean("moocApplicationContext") + "可以通过他对application的" +
                "初始化进行一些操作");
    }
}
