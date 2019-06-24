package com.wind.spring.springjdbc;

import com.wind.spring.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void select() {
//        String sql = "insert into book(name,number) values('测试课本',123)";
//        jdbcTemplate.update(sql);
        String sql = "select * from book";
        List<Book> books = jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                book.setBook_id(resultSet.getString("book_id"));
                book.setNumber(resultSet.getString("number"));
                return book;
            }
        });

        System.out.println(books);
    }

    public void update() {
        String sql = "insert into book(name,number) values('测试课本',123)";
        jdbcTemplate.update(sql);
    }


}
