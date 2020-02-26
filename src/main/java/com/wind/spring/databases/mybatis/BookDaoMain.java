package com.wind.spring.databases.mybatis;

import com.wind.spring.other.bean.Book;
import com.wind.spring.databases.mybatis.dao.BookAnnotationDao;
import com.wind.spring.databases.mybatis.dao.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * spring 配合mybatis的使用
 */
public class BookDaoMain {

    private static Logger logger = LoggerFactory.getLogger(BookDaoMain.class);

    private static ApplicationContext ac = getApplication();

    private static BookDao bookDao;

    private static BookAnnotationDao bookAnnotationDao;

    public static void main(String args[]) {

        queryAll();

        queryAll2();
    }


    /**
     * 基于mybatis xml的使用的搜索输出
     */
    public static void queryAll() {
        bookDao = (BookDao) ac.getBean("bookDao");
        List<Book> books = bookDao.queryAll(0, 4);
        for (Book book : books) {
            logger.debug("基于sql的xml的Mybatis搜索全部课本输出：" + book.toString());
        }
    }


    public static void queryAll2() {
        bookAnnotationDao = (BookAnnotationDao) ac.getBean("bookAnnotationDao");
        List<Book> books = bookAnnotationDao.queryAll(0, 4);
        for (Book book : books) {
            logger.debug("基于注解的Mybatis搜索全部课本输出：" + book.toString());
        }
    }


    /**
     * 获取spring的配置文件，生成spring的applicationContext
     *
     * @return ApplicationContext spring上下文
     */
    private static ApplicationContext getApplication() {
        ApplicationContext ac = new
                ClassPathXmlApplicationContext("spring/application-jdbc.xml");
        return ac;
    }

}
