package jdbc;

import jdbc.bean.Book;
import jdbc.dao.JDBCBookDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;


@Slf4j
public class JdbcTest {

    private static JDBCBookDao dao;

    @BeforeClass
    public static void beforeClass() {
        dao = new JDBCBookDao();
    }

    @Test
    public void insert() throws SQLException {
        Book book = new Book();
        book.setName("添加的书本");
        dao.insert(book);
    }

    @Test
    public void queryAll() {
        for (Book book : dao.queryAll()) {
            log.info(book.toString());
        }
    }

    @Test
    public void delete(){
        Book book = new Book();
        book.setBook_id(5);
        dao.delete(book);
    }
    @Test
    public void update(){
        Book book = new Book();
        book.setBook_id(6);
        book.setName("修改的课本名字");
        dao.update(book);
    }

    @Test
    public void query(){
        Book book = new Book();
        book.setBook_id(1);
        log.info(dao.query(book).toString());
    }
}
