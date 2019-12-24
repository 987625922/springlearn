package com.wind.spring.data.mybatis.dao;

import com.wind.spring.bean.Book;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 基于注解的spring mybatis使用
 */
public interface BookAnnotationDao {

    @Select("SELECT book_id,name,number FROM book ORDER BY book_id LIMIT #{offset}, #{limit}")
    List<Book> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
