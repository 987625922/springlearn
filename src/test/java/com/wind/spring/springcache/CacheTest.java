package com.wind.spring.springcache;

import com.wind.spring.other.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//指定单元测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/spring/application-bean.xml"})
public class CacheTest {

    @Autowired
    CacheUseService cacheUseService;

    @Test
    public void test1() {
        cacheUseService.getValueByName("123");
        cacheUseService.getValueByName("123");
        cacheUseService.getValueByName("12");
        Book book = new Book();
        book.setName("12345");
        cacheUseService.updateAccount(book);
        cacheUseService.getValueByName("123");
    }

}
