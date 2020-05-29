package org;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.autodiscovery.RequiredMovieLister;
import org.learn.common.bean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring中基于application-bean.xml配置文件的bean的使用
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-bean.xml"})
public class SpringBeanTest {


    private ApplicationContext context =
            new ClassPathXmlApplicationContext("/application-bean.xml");

    public Object getBean(String beanId) {
        log.info("application的信息:" + context);
        return context.getBean(beanId);
    }

    @Test
    public void test() {
        User userBean = (User) getBean("userbean");
        log.info("SpringTest中获取到UserBean：" + userBean.toString());
    }


    /**
     * 自动装配类中的成员变量
     */
    @Test
    public void testAuto() {
        log.info(getBean("autobean").toString());
    }


    /**
     * @Required,@PostConstruct和@Qualifier和@Autowired的使用
     */
    @Test
    public void testRequired() {
        RequiredMovieLister requiredMovieLister =
                (RequiredMovieLister) getBean("requiredMovieLister");
        log.info(requiredMovieLister.getMovieFinder().getClass().getName());
    }


}
