package org.learn.dao;

import org.learn.common.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Spring 事务的使用
 */
@Repository
public class BookDao {

    // JdbcTemplate是Spring对JDBC的封装
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * 已在application-jdbc中配置本方法是基于xml的事务
     */
    public void updateTranscactionalXml() {
        String sql = "insert into book(name,number) values('事务的测试','100')";
        jdbcTemplate.update(sql);
//        int i = 1/0; //抛出异常
    }

    //使用注解的方式实现事务
    @Transactional
    public void upTransactionaldate() {
        String sql = "insert into book(name,number) values('事务的测试','100')";
        jdbcTemplate.update(sql);
        int i = 1 / 0; //抛出异常
    }

    public List<Book> select() {
        String sql = "select * from book";
        List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                book.setId(resultSet.getInt("id"));
                book.setNumber(resultSet.getString("number"));
                return book;
            }
        });
        return books;
    }
}
