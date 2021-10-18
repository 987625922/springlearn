package test;

import com.learn.SpringDaoApplication;
import com.learn.bean.Book;
import com.learn.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDaoApplication.class)
@FixMethodOrder(MethodSorters.JVM)
public class DaoTest {

    @Autowired
    BookService bookService;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        log.info("deleteById ==> {}", 1);
        bookService.deleteById(1);
        log.info("deleteById ==> {}", 2);
        bookService.deleteById(2);
        //触发事务
//        bookService.deleteById(3);
        Book book = new Book();
        book.setId(1);
        book.setBookIndex("12");
        book.setName("百科全书");
        book.setNumber(12);
        log.info("insert ==> {}", book);
        bookService.insert(book);
        Book book1 = new Book();
        book1.setId(2);
        book1.setBookIndex("12");
        book1.setName("百科全书2");
        book1.setNumber(12);
        log.info("insert ==> {}", book1);
        bookService.insert(book1);
    }

    @Test
    public void selectList() {
        bookService.selectList().forEach(new Consumer<Book>() {
            @Override
            public void accept(Book book) {
                log.info("selectList ==> {}", book);
            }
        });
    }

    @Test
    public void update() {
        Book book1 = new Book();
        book1.setId(2);
        book1.setBookIndex("12");
        book1.setName("百科全书3");
        book1.setNumber(12);
        log.info("update ==> {}", book1);
        bookService.update(book1);
    }

    @Test
    public void selectById() {
        log.info("selectById ==> {}", bookService.selectById(2));
    }
}
