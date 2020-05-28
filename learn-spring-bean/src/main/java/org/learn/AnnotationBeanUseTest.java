package org.learn;

import org.learn.common.bean.Book;
import com.wind.spring.springbean.annotationbean.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 类配置文件的使用，用来替换application-bean.xml
 */
public class AnnotationBeanUseTest {

    private static Logger logger = LoggerFactory.getLogger(AnnotationBeanUseTest.class);

    public static void main(String args[]) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //通过类配置获取到Book的bean
        Book book = (Book) context.getBean("myBook");
        logger.debug(book.toString());

        //通过类配置文件获取到基于泛型的自动装配的Stroe
        AppConfig.Store store = (AppConfig.Store) context.getBean("storeTest");
        logger.debug(store.toString());
    }
}
