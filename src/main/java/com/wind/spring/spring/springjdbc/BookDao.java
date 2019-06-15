package com.wind.spring.spring.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void select(){
        String sql = "insert into book(name,number) values('测试课本',123)";
        jdbcTemplate.update(sql);

    }

}
