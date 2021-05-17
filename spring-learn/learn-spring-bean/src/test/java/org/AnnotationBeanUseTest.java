package org;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.annotationbean.AppConfig;
import org.learn.common.bean.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 类配置文件的使用，用来替换application-bean.xml
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-bean.xml"})
public class AnnotationBeanUseTest {

    @Test
    public void test() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //通过类配置获取到Book的bean
        Book book = (Book) context.getBean("myBook");
        log.info(book.toString());

        //通过类配置文件获取到基于泛型的自动装配的Stroe
        AppConfig.Store store = (AppConfig.Store) context.getBean("storeTest");
        log.info(store.toString());
    }
}
