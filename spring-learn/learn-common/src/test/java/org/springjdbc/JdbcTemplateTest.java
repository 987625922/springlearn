package org.springjdbc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.common.bean.Book;
import org.learn.common.springjdbc.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring 自带对数据库的jdbc封装的springjdbc的使用和对spring事务的使用
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-jdbc.xml"})
public class JdbcTemplateTest {

    @Autowired
    private BookDao bookDao;

    //xml实现事务
    @Test
    public void update() {
        bookDao.updateTranscactionalXml();
    }

    //注解方式实现事务
    @Test
    public void update2() {
        bookDao.upTransactionaldate();
    }

    @Test
    public void select() {
        List<Book> bookList = bookDao.select();
        for (Book book : bookList) {
            log.info(book.toString());
        }
    }

}
