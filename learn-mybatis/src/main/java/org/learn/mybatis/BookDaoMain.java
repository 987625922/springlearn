package org.learn.mybatis;

import com.wind.spring.databases.mybatis.dao.BookAnnotationDao;
import com.wind.spring.databases.mybatis.dao.BookDao;
import org.learn.common.bean.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring 配合mybatis的使用
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-mybatis.xml"})
public class BookDaoMain {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookAnnotationDao bookAnnotationDao;

    /**
     * 基于mybatis xml的使用的搜索输出
     */
    @Test
    public void queryAll() {
        List<Book> books = bookDao.queryAll(0, 4);
        for (Book book : books) {
            log.debug("基于sql的xml的Mybatis搜索全部课本输出：" + book.toString());
        }
    }

    @Test
    public void queryAll2() {
        List<Book> books = bookAnnotationDao.queryAll(0, 4);
        for (Book book : books) {
            log.debug("基于注解的Mybatis搜索全部课本输出：" + book.toString());
        }
    }

}
