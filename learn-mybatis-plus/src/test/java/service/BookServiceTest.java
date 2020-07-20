package service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.mp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: LL
 * @Description: BookService的测试类
 * @Date:Create：in 2020/7/20 14:21
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/application-mybatis.xml"})
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void selectAll() {
        bookService.getAllList().forEach(System.out::println);
    }
}
