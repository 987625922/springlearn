package service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.mp.domain.Book;
import org.learn.mp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 书本实体类的service
     */
    @Autowired
    private BookService bookService;

    /**
     * 获取所有的书本
     */
    @Test
    public void selectAll() {
        bookService.getAllList().forEach(System.out::println);
    }

    /**
     * 添加一个课本
     */
    @Test
    public void insert() {
        Book book = new Book();
        book.setName("测试的课本");
        book.setNumber(12);
        bookService.insert(book);
    }

    /**
     * 根据id搜索课本
     */
    @Test
    public void selectById() {
        log.info("selectById() =====> " + bookService.selectById(1L));
    }

    /**
     * 根据创建时间搜索书本
     */
    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("create_time", "2020-07-20 00:00:00.000000");
        bookService.selectByMap(map).forEach(System.out::println);
    }

    /**
     * 根据id列表搜索书本
     */
    @Test
    public void selectBatchIds() {
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);
        bookService.selectBatchIds(idList).forEach(System.out::println);
    }

    /**
     * mapper的selectOne的使用
     * 如果不止一个就会抛出异常
     */
    @Test
    public void selectOne() {
        log.info("selectOne()======>" + bookService.selectOne("测试的课本1"));
    }

    /**
     * 模糊搜索课本名
     */
    @Test
    public void selectList() {
        bookService.selectList("课本").forEach(System.out::println);
    }

    /**
     * 有选择的搜索几个列名
     */
    @Test
    public void selectMaps() {
        bookService.selectMaps("课本").forEach(System.out::println);
    }

    /**
     * 输出聚合函数的值(selectMaps的场景)
     */
    @Test
    public void selectMaps2() {
        bookService.selectMaps2("课本").forEach(System.out::println);
    }

    /**
     * 返回总记录数
     */
    @Test
    public void selectCount() {
        log.info("总记录数=====>" + bookService.selectCount("课本"));
    }

    /**
     * 根据id更新课本
     */
    @Test
    public void updateById() {
        Book book = new Book();
        book.setId(1L);
        book.setName("更新过的课本");
        bookService.updateById(book);
    }

    /**
     * 删除数据库中create_time为2020-07-20 23:16:30.447000的item
     */
    @Test
    public void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("create_time", "2020-07-20 23:16:30.447000");
        bookService.deleteByMap(map);
    }
}
